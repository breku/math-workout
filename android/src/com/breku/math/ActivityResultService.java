package com.breku.math;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;

import java.util.ArrayList;

import static com.breku.math.AndroidConstants.RC_SELECT_PLAYERS;

/**
 * Created by brekol on 11.10.16.
 */
public class ActivityResultService {

    private final AndroidLauncher androidLauncher;

    public ActivityResultService(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;
    }

    public void onInvitePlayersScreenActivityResult(int request, int response, Intent data) {
        if (request == RC_SELECT_PLAYERS) {
            if (response != Activity.RESULT_OK) {
                // user canceled
                return;
            }
            createMatch(data);
        }
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
