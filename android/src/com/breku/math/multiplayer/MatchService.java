package com.breku.math.multiplayer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.breku.math.AndroidLauncher;
import com.breku.math.integration.IntegrationCallbackModel;
import com.breku.math.integration.LaunchCallback;
import com.breku.math.persistance.TurnData;
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
    private TurnData mTurnData;
    private LaunchCallback launchCallbackFormActivityResult;

    public MatchService(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;
    }

    public void processResult(TurnBasedMultiplayer.InitiateMatchResult result, LaunchCallback launchCallback) {
        TurnBasedMatch match = result.getMatch();

        if (!checkStatusCode(match, result.getStatus().getStatusCode())) {
            return;
        }

        if (match.getData() != null) {
            // This is a game that has already started, so I'll just start
            updateMatch(match, launchCallback);
            return;
        }

        startMatch(match, launchCallback);
    }


    public void processResult(TurnBasedMultiplayer.UpdateMatchResult result, LaunchCallback launchCallback) {
        TurnBasedMatch match = result.getMatch();
        if (!checkStatusCode(match, result.getStatus().getStatusCode())) {
            return;
        }


        isDoingTurn = (match.getTurnStatus() == TurnBasedMatch.MATCH_TURN_STATUS_MY_TURN);

        if (isDoingTurn) {
            updateMatch(match, launchCallback);
            return;
        }

    }

    // This is the main function that gets called when players choose a match
    // from the inbox, or else create a match and want to start it.
    public void updateMatch(TurnBasedMatch match, LaunchCallback launchCallback) {
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
                mTurnData = TurnData.unpersist(mMatch.getData());
                Log.e(TAG, "setGameplayUI");
                final LaunchCallback correctLaunchCallback = getCorrectLaunchCallback(launchCallback);
                IntegrationCallbackModel callbackModel = new IntegrationCallbackModel(mTurnData.levelDifficulty, mTurnData.gameType);
                correctLaunchCallback.setIntegrationCallbackModel(callbackModel);
                correctLaunchCallback.onSucces();

                return;
            case TurnBasedMatch.MATCH_TURN_STATUS_THEIR_TURN:
                // Should return results.
                showWarning("Alas...", "It's not your turn.", androidLauncher.getContext());
                break;
            case TurnBasedMatch.MATCH_TURN_STATUS_INVITED:
                showWarning("Good inititative!",
                        "Still waiting for invitations.\n\nBe patient!", androidLauncher.getContext());
        }

        mTurnData = null;
    }

    // startMatch() happens in response to the createTurnBasedMatch()
    // above. This is only called on success, so we should have a
    // valid match object. We're taking this opportunity to setup the
    // game, saving our initial state. Calling takeTurn() will
    // callback to OnTurnBasedMatchUpdated(), which will show the game
    // UI.
    public void startMatch(TurnBasedMatch match, final LaunchCallback launchCallback) {
        mTurnData = new TurnData();
        // Some basic turn data
        mTurnData.data = "First turn";
        final IntegrationCallbackModel callbackModel = launchCallback.getIntegrationCallbackModel();
        mTurnData.levelDifficulty = callbackModel.getLevelDifficulty();
        mTurnData.gameType = callbackModel.getGameType();

        mMatch = match;

        String playerId = Games.Players.getCurrentPlayerId(androidLauncher.getGameHelper().getApiClient());
        String myParticipantId = mMatch.getParticipantId(playerId);


        Games.TurnBasedMultiplayer.takeTurn(androidLauncher.getGameHelper().getApiClient(), match.getMatchId(),
                mTurnData.persist(), myParticipantId).setResultCallback(
                new ResultCallback<TurnBasedMultiplayer.UpdateMatchResult>() {
                    @Override
                    public void onResult(TurnBasedMultiplayer.UpdateMatchResult result) {
                        processResult(result, launchCallback);
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
                processResult(result, null);
            }
        };
        Games.TurnBasedMultiplayer.createMatch(androidLauncher.getGameHelper().getApiClient(), tbmc).setResultCallback(cb);

    }

    public void setLaunchCallbackFormActivityResult(LaunchCallback launchCallbackFormActivityResult) {
        this.launchCallbackFormActivityResult = launchCallbackFormActivityResult;
    }

    private LaunchCallback getCorrectLaunchCallback(LaunchCallback launchCallback) {
        if (launchCallback != null) {
            return launchCallback;
        }
        return launchCallbackFormActivityResult;
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