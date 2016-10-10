package com.breku.math;

import com.badlogic.gdx.Gdx;
import com.breku.math.googleplay.GoogleApiService;
import com.google.example.games.basegameutils.GameHelper;

/**
 * Created by brekol on 10.10.16.
 */
public class AndroidGoogleApiServiceImpl implements GoogleApiService {

    private static final String TAG = "AndroidGoogleApiServiceImpl";
    private static final int RC_SELECT_PLAYERS = 1000;
    private final AndroidLauncher androidLauncher;


    public AndroidGoogleApiServiceImpl(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;

    }

    @Override
    public void launchInvitePlayersScreen() {
        Gdx.app.log(TAG, "launchInvitePlayersScreen");
        Gdx.app.log(TAG, String.format("gameHelper.isConnecting()=%s, gameHelper.isSignedIn()=%s", androidLauncher.getGameHelper(), androidLauncher.getGameHelper().isSignedIn()));


//        Intent intent =
//                Games.TurnBasedMultiplayer.getSelectOpponentsIntent(gameHelper.getApiClient(), 1, 7, true);
//        androidLauncher.startActivityForResult(intent, RC_SELECT_PLAYERS);
    }
}
