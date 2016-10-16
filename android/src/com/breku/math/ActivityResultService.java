package com.breku.math;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.breku.math.persistance.TurnData;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;

import java.util.ArrayList;

import static com.breku.math.AndroidConstants.RC_INBOX;
import static com.breku.math.AndroidConstants.RC_SELECT_PLAYERS;
import static com.breku.math.util.MessageUtil.showWarning;

/**
 * Created by brekol on 11.10.16.
 */
public class ActivityResultService {

    private static final String TAG = "ActivityResultService";
    private final AndroidLauncher androidLauncher;


    // This is the current match we're in; null if not loaded
    public TurnBasedMatch mMatch;
    private TurnData mTurnData;

    public ActivityResultService(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;
    }

    public void onActivityResult(int request, int response, Intent data) {
        Log.i(TAG, String.format("onActivityResult request=[%s] response=[%s], data=[%s]", request, response, data));
        if (request == RC_SELECT_PLAYERS) {
            Log.i(TAG, "Request select players");
            onInvitePlayersScreenActivityResult(request, response, data);
        } else if (request == RC_INBOX) {
            Log.i(TAG, "Request inbox");
            // user canceled
            if (response != Activity.RESULT_OK) {
                return;
            }

            final TurnBasedMatch match = data.getParcelableExtra(Multiplayer.EXTRA_TURN_BASED_MATCH);
            if (match != null) {
                updateMatch(match);
            }
        }
    }

    public void onInvitePlayersScreenActivityResult(int request, int response, Intent data) {
        if (response != Activity.RESULT_OK) {
            // user canceled
            return;
        }
        createMatch(data);
    }

    // This is the main function that gets called when players choose a match
    // from the inbox, or else create a match and want to start it.
    public void updateMatch(TurnBasedMatch match) {
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
//                setGameplayUI();
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

//        setViewVisibility();
    }

    private void createMatch(Intent data) {
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

        // Create and start the match.
        Games.TurnBasedMultiplayer
                .createMatch(androidLauncher.getGameHelper().getApiClient(), tbmc)
                .setResultCallback(new MatchInitiatedCallback());
    }


}
