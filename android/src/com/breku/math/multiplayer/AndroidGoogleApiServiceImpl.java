package com.breku.math.multiplayer;

import android.content.Intent;
import android.os.Bundle;
import com.badlogic.gdx.Gdx;
import com.breku.math.AndroidLauncher;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.integration.LaunchCallback;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.example.games.basegameutils.GameHelper;

import static com.breku.math.util.AndroidConstants.RC_INBOX;
import static com.breku.math.util.AndroidConstants.RC_SELECT_PLAYERS;

/**
 * Created by brekol on 10.10.16.
 */
public class AndroidGoogleApiServiceImpl implements GoogleApiService {

    private static final String TAG = "AndroidGoogleApiServiceImpl";

    private final AndroidLauncher androidLauncher;
    private final MatchService matchService;


    public AndroidGoogleApiServiceImpl(AndroidLauncher androidLauncher, final MatchService matchService) {
        this.androidLauncher = androidLauncher;
        this.matchService = matchService;

    }

    @Override
    public void launchInvitePlayersScreen() {
        Gdx.app.log(TAG, "launchInvitePlayersScreen");
        final GameHelper gameHelper = androidLauncher.getGameHelper();
        Gdx.app.log(TAG, String.format("gameHelper.isConnecting()=%s, gameHelper.isSignedIn()=%s", gameHelper, gameHelper.isSignedIn()));

        Intent intent =
                Games.TurnBasedMultiplayer.getSelectOpponentsIntent(gameHelper.getApiClient(), 1, 7, true);
        androidLauncher.startActivityForResult(intent, RC_SELECT_PLAYERS);
    }

    @Override
    public void launchQuickGame(final LaunchCallback launchCallback) {
        Gdx.app.log(TAG, "launchQuickGame");

        final Bundle autoMatchCriteria = RoomConfig.createAutoMatchCriteria(1, 1, 0);
        final TurnBasedMatchConfig tbmc = TurnBasedMatchConfig.builder().setAutoMatchCriteria(autoMatchCriteria).build();

        // Start the match
        ResultCallback<TurnBasedMultiplayer.InitiateMatchResult> cb = new ResultCallback<TurnBasedMultiplayer.InitiateMatchResult>() {
            @Override
            public void onResult(TurnBasedMultiplayer.InitiateMatchResult result) {
                matchService.processResult(result, launchCallback);
            }
        };
        Games.TurnBasedMultiplayer.createMatch(androidLauncher.getGameHelper().getApiClient(), tbmc).setResultCallback(cb);
    }

    @Override
    public void launchInbox(LaunchCallback launchCallback) {
        Gdx.app.log(TAG, "launchInbox");
        Intent intent = Games.TurnBasedMultiplayer.getInboxIntent(androidLauncher.getGameHelper().getApiClient());
        androidLauncher.startActivityForResult(intent, RC_INBOX);
        matchService.setLaunchCallbackFormActivityResult(launchCallback);
    }
}