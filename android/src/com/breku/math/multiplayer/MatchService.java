package com.breku.math.multiplayer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.breku.math.AndroidLauncher;
import com.breku.math.integration.GameIntegrationCallbackValue;
import com.breku.math.integration.GoogleCallback;
import com.breku.math.integration.persistance.Round;
import com.breku.math.integration.persistance.TurnData;
import com.breku.math.integration.persistance.TurnDataService;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

import java.util.ArrayList;

import static com.breku.math.util.MessageUtil.showWarning;

/**
 * Created by brekol on 17.10.16.
 */
public class MatchService {

    private static final String TAG = "MatchService";
    private final AndroidLauncher androidLauncher;
    // This is the current match we're in; null if not loaded
    public TurnBasedMatch mMatch;
    // Should I be showing the turn API?
    public boolean isDoingTurn = false;
    private TurnDataService turnDataService = new TurnDataService();
    private GoogleCallback googleCallbackFormActivityResult;

    public MatchService(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;
    }

    public void processResult(TurnBasedMultiplayer.InitiateMatchResult result, GoogleCallback<GameIntegrationCallbackValue> googleCallback) {
        TurnBasedMatch match = result.getMatch();

        if (!checkStatusCode(match, result.getStatus().getStatusCode())) {
            googleCallback.onFailure();
            return;
        }

        if (match.getData() != null) {
            // This is a game that has already started, so I'll just start
            updateMatch(match, googleCallback);
            return;
        }

        startMatch(match, googleCallback);
    }


    public void processResult(TurnBasedMultiplayer.UpdateMatchResult result, GoogleCallback googleCallback) {
        TurnBasedMatch match = result.getMatch();
        if (!checkStatusCode(match, result.getStatus().getStatusCode())) {
            return;
        }


        isDoingTurn = (match.getTurnStatus() == TurnBasedMatch.MATCH_TURN_STATUS_MY_TURN);

        if (isDoingTurn) {
            updateMatch(match, googleCallback);
            return;
        }

    }

    // This is the main function that gets called when players choose a match
    // from the inbox, or else create a match and want to start it.
    public void updateMatch(TurnBasedMatch match, GoogleCallback googleCallback) {
        mMatch = match;

        int status = match.getStatus();
        int turnStatus = match.getTurnStatus();

        switch (status) {
            case TurnBasedMatch.MATCH_STATUS_CANCELED:
                showWarning("Canceled!", "This game was canceled!", androidLauncher.getContext());
                return;
            case TurnBasedMatch.MATCH_STATUS_EXPIRED:
                showWarning("Expired!", "This game is expired.  So sad!", androidLauncher.getContext());
                return;
            case TurnBasedMatch.MATCH_STATUS_AUTO_MATCHING:
                showWarning("Waiting for auto-match...",
                        "We're still waiting for an automatch partner.", androidLauncher.getContext());
                return;
            case TurnBasedMatch.MATCH_STATUS_COMPLETE:
                if (turnStatus == TurnBasedMatch.MATCH_TURN_STATUS_COMPLETE) {
                    showWarning(
                            "Complete!",
                            "This game is over; someone finished it, and so did you!  There is nothing to be done.", androidLauncher.getContext());
                    break;
                }

                // Note that in this state, you must still call "Finish" yourself,
                // so we allow this to continue.
                showWarning("Complete!",
                        "This game is over; someone finished it!  You can only finish it now.", androidLauncher.getContext());
        }

        // OK, it's active. Check on turn status.
        switch (turnStatus) {
            case TurnBasedMatch.MATCH_TURN_STATUS_MY_TURN:
                TurnData turnData = turnDataService.unpersist(mMatch.getData());
                final String myParticipantIdFromCurrentMatch = getMyParticipantIdFromCurrentMatch();
                // callback might be in field because it might come from inboxLaunch or friendScreen (new intent was started)
                final GoogleCallback correctGoogleCallback = getCorrectLaunchCallback(googleCallback);

                turnDataService.setTurnData(turnData);
                if (turnData.getPlayersNameMap().get(myParticipantIdFromCurrentMatch) == null) {
                    takeTurnAsMyselfUpdateName(correctGoogleCallback);
                    return;
                }

                Log.e(TAG, "setGameplayUI");


                final Round currentRound = turnData.getCurrentRound();
                final GameIntegrationCallbackValue callbackValue = new GameIntegrationCallbackValue(currentRound.getLevelDifficulty(), currentRound.getGameType());
                if ((turnData.isFirstRound() || currentRound.getScoreMap().size() < 2) && currentRound.getLevelDifficulty() == null) {
                    callbackValue.setShouldSetGameType(true);
                } else {
                    callbackValue.setShouldSetGameType(false);
                }
                callbackValue.setTurnData(turnData);
                correctGoogleCallback.setCallbackValue(callbackValue);
                correctGoogleCallback.onSucces();

                return;
            case TurnBasedMatch.MATCH_TURN_STATUS_THEIR_TURN:
                // Should return results.
                showWarning("Alas...", "It's not your turn.", androidLauncher.getContext());
                break;
            case TurnBasedMatch.MATCH_TURN_STATUS_INVITED:
                showWarning("Good inititative!",
                        "Still waiting for invitations.\n\nBe patient!", androidLauncher.getContext());
        }

        turnDataService.clearTurnData();
    }

    // startMatch() happens in response to the createTurnBasedMatch()
    // above. This is only called on success, so we should have a
    // valid match object. We're taking this opportunity to setup the
    // game, saving our initial state. Calling takeTurn() will
    // callback to OnTurnBasedMatchUpdated(), which will show the game
    // UI.
    public void startMatch(TurnBasedMatch match, final GoogleCallback<GameIntegrationCallbackValue> googleCallback) {

        TurnData turnData = new TurnData();
        // Some basic turn data
        turnData.setData("First turn");
//        final GameIntegrationCallbackValue callbackModel = googleCallback.getCallbackValue();
//        turnData.setLevelDifficulty(callbackModel.getLevelDifficulty());
//        turnData.setGameType(callbackModel.getGameType());
        turnDataService.setTurnData(turnData);

        mMatch = match;

        String playerId = Games.Players.getCurrentPlayerId(androidLauncher.getGameHelper().getApiClient());
        String myParticipantId = mMatch.getParticipantId(playerId);
        final String displayName = Games.Players.getCurrentPlayer(androidLauncher.getGameHelper().getApiClient()).getDisplayName();
        turnDataService.updateName(myParticipantId, displayName);


        Games.TurnBasedMultiplayer.takeTurn(androidLauncher.getGameHelper().getApiClient(), match.getMatchId(),
                turnDataService.persist(), myParticipantId).setResultCallback(
                new ResultCallback<TurnBasedMultiplayer.UpdateMatchResult>() {
                    @Override
                    public void onResult(TurnBasedMultiplayer.UpdateMatchResult result) {
                        processResult(result, googleCallback);
                    }
                });
    }

    public void createMatch(Intent data) {
        // Get the invitee list.
        final ArrayList<String> invitees =
                data.getStringArrayListExtra(Games.EXTRA_PLAYER_IDS);

        // Get auto-match criteria.
        Bundle autoMatchCriteria = null;
        int minAutoMatchPlayers = data.getIntExtra(
                Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, 0);
        int maxAutoMatchPlayers = data.getIntExtra(
                Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, 0);
        if (minAutoMatchPlayers > 0) {
            autoMatchCriteria = RoomConfig.createAutoMatchCriteria(
                    minAutoMatchPlayers, maxAutoMatchPlayers, 0);
        } else {
            autoMatchCriteria = null;
        }

        TurnBasedMatchConfig tbmc = TurnBasedMatchConfig.builder()
                .addInvitedPlayers(invitees)
                .setAutoMatchCriteria(autoMatchCriteria)
                .build();


        ResultCallback<TurnBasedMultiplayer.InitiateMatchResult> cb = new ResultCallback<TurnBasedMultiplayer.InitiateMatchResult>() {
            @Override
            public void onResult(TurnBasedMultiplayer.InitiateMatchResult result) {
                processResult(result, googleCallbackFormActivityResult);
            }
        };
        Games.TurnBasedMultiplayer.createMatch(androidLauncher.getGameHelper().getApiClient(), tbmc).setResultCallback(cb);

    }

    public void setGoogleCallbackFormActivityResult(GoogleCallback googleCallbackFormActivityResult) {
        this.googleCallbackFormActivityResult = googleCallbackFormActivityResult;
    }

    public void takeTurn(final GoogleCallback<GameIntegrationCallbackValue> googleCallback) {

        final int currentPlayerScore = googleCallback.getCallbackValue().getScore();
        final String myParticipantId = getMyParticipantIdFromCurrentMatch();

        turnDataService.updateScoreForPlayer(myParticipantId, currentPlayerScore);
        turnDataService.incrementTurnCounter();

        final String nextParticipantId = getNextParticipantId();
        Games.TurnBasedMultiplayer.takeTurn(androidLauncher.getGameHelper().getApiClient(), mMatch.getMatchId(),
                turnDataService.persist(), nextParticipantId).setResultCallback(
                new ResultCallback<TurnBasedMultiplayer.UpdateMatchResult>() {
                    @Override
                    public void onResult(TurnBasedMultiplayer.UpdateMatchResult result) {
                        processResult(result, googleCallback);
                    }
                });
        turnDataService.clearTurnData();
    }

    public String getNextParticipantId() {

        String playerId = Games.Players.getCurrentPlayerId(androidLauncher.getGameHelper().getApiClient());
        String myParticipantId = mMatch.getParticipantId(playerId);

        ArrayList<String> participantIds = mMatch.getParticipantIds();

        int desiredIndex = -1;

        for (int i = 0; i < participantIds.size(); i++) {
            if (participantIds.get(i).equals(myParticipantId)) {
                desiredIndex = i + 1;
            }
        }

        if (desiredIndex < participantIds.size()) {
            return participantIds.get(desiredIndex);
        }

        if (mMatch.getAvailableAutoMatchSlots() <= 0) {
            // You've run out of automatch slots, so we start over.
            return participantIds.get(0);
        } else {
            // You have not yet fully automatched, so null will find a new
            // person to play against.
            return null;
        }
    }

    public void takeTurnAsMyself(final GoogleCallback<GameIntegrationCallbackValue> googleCallback, boolean incrementTurnCounter) {

        final GoogleApiClient googleApiClient = androidLauncher.getGameHelper().getApiClient();
        final String playerId = Games.Players.getCurrentPlayerId(googleApiClient);
        final String myParticipantId = mMatch.getParticipantId(playerId);
        final String displayName = Games.Players.getCurrentPlayer(androidLauncher.getGameHelper().getApiClient()).getDisplayName();

        final GameIntegrationCallbackValue callbackValue = googleCallback.getCallbackValue();
        turnDataService.updateLevelAndGameType(callbackValue.getLevelDifficulty(), callbackValue.getGameType());
        turnDataService.updateName(myParticipantId, displayName);
        if (callbackValue.getScore() != null) {
            turnDataService.updateScoreForPlayer(myParticipantId, callbackValue.getScore());
        }

        if (incrementTurnCounter) {
            turnDataService.incrementTurnCounter();
        }

        Games.TurnBasedMultiplayer.takeTurn(googleApiClient, mMatch.getMatchId(),
                turnDataService.persist(), myParticipantId).setResultCallback(
                new ResultCallback<TurnBasedMultiplayer.UpdateMatchResult>() {
                    @Override
                    public void onResult(TurnBasedMultiplayer.UpdateMatchResult result) {
                        processResult(result, googleCallback);
                    }
                });

    }

    private void takeTurnAsMyselfUpdateName(final GoogleCallback googleCallback) {
        final GoogleApiClient googleApiClient = androidLauncher.getGameHelper().getApiClient();
        final String myParticipantId = getMyParticipantIdFromCurrentMatch();
        final String displayName = Games.Players.getCurrentPlayer(androidLauncher.getGameHelper().getApiClient()).getDisplayName();
        turnDataService.updateName(myParticipantId, displayName);

        Games.TurnBasedMultiplayer.takeTurn(googleApiClient, mMatch.getMatchId(),
                turnDataService.persist(), myParticipantId).setResultCallback(
                new ResultCallback<TurnBasedMultiplayer.UpdateMatchResult>() {
                    @Override
                    public void onResult(TurnBasedMultiplayer.UpdateMatchResult result) {
                        processResult(result, googleCallback);
                    }
                });
    }

    private String getMyParticipantIdFromCurrentMatch() {
        String playerId = Games.Players.getCurrentPlayerId(androidLauncher.getGameHelper().getApiClient());
        return mMatch.getParticipantId(playerId);
    }

    private GoogleCallback getCorrectLaunchCallback(GoogleCallback googleCallback) {
        if (googleCallback != null) {
            return googleCallback;
        }
        return googleCallbackFormActivityResult;
    }

    // Returns false if something went wrong, probably. This should handle
    // more cases, and probably report more accurate results.
    private boolean checkStatusCode(TurnBasedMatch match, int statusCode) {
        switch (statusCode) {
            case GamesStatusCodes.STATUS_OK:
                return true;
            case GamesStatusCodes.STATUS_NETWORK_ERROR_OPERATION_DEFERRED:
                // This is OK; the action is stored by Google Play Services and will
                // be dealt with later.
                Toast.makeText(
                        androidLauncher.getContext(),
                        "Stored action for later.  (Please remove this toast before release.)",
                        Toast.LENGTH_SHORT).show();
                // NOTE: This toast is for informative reasons only; please remove
                // it from your final application.
                return true;
            case GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_NOT_TRUSTED_TESTER:
                showWarning("status_multiplayer_error_not_trusted_tester", androidLauncher.getContext());
                break;
            case GamesStatusCodes.STATUS_MATCH_ERROR_ALREADY_REMATCHED:
                showWarning("match_error_already_rematched", androidLauncher.getContext());
                break;
            case GamesStatusCodes.STATUS_NETWORK_ERROR_OPERATION_FAILED:
                showWarning("network_error_operation_failed", androidLauncher.getContext());
                break;
            case GamesStatusCodes.STATUS_CLIENT_RECONNECT_REQUIRED:
                showWarning("client_reconnect_required", androidLauncher.getContext());
                break;
            case GamesStatusCodes.STATUS_INTERNAL_ERROR:
                showWarning("internal_error", androidLauncher.getContext());
                break;
            case GamesStatusCodes.STATUS_MATCH_ERROR_INACTIVE_MATCH:
                showWarning("match_error_inactive_match", androidLauncher.getContext());
                break;
            case GamesStatusCodes.STATUS_MATCH_ERROR_LOCALLY_MODIFIED:
                showWarning("match_error_locally_modified", androidLauncher.getContext());
                break;
            default:
                showWarning("unexpected_status", androidLauncher.getContext());
                Log.d(TAG, "Did not have warning or string to deal with: "
                        + statusCode);
        }

        return false;
    }
}
