package com.breku.math.multiplayer.result;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.breku.math.AndroidLauncher;
import com.breku.math.multiplayer.MatchService;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;

import static com.breku.math.util.AndroidConstants.RC_INBOX;
import static com.breku.math.util.AndroidConstants.RC_SELECT_PLAYERS;

/**
 * Created by brekol on 11.10.16.
 */
public class ActivityResultService {

    private static final String TAG = "ActivityResultService";
    private final AndroidLauncher androidLauncher;
    private final MatchService matchService;


    public ActivityResultService(AndroidLauncher androidLauncher, MatchService matchService) {
        this.androidLauncher = androidLauncher;
        this.matchService = matchService;
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
                matchService.updateMatch(match, null);
            }
        }
    }

    public void onInvitePlayersScreenActivityResult(int request, int response, Intent data) {
        if (response != Activity.RESULT_OK) {
            // user canceled
            return;
        }
        matchService.createMatch(data);
    }


}
