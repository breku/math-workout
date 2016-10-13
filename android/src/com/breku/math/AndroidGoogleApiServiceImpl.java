package com.breku.math;

import android.content.Intent;
import com.badlogic.gdx.Gdx;
import com.breku.math.googleplay.GoogleApiService;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

import static com.breku.math.AndroidConstants.RC_SELECT_PLAYERS;

/**
 * Created by brekol on 10.10.16.
 */
public class AndroidGoogleApiServiceImpl implements GoogleApiService {

    private static final String TAG = "AndroidGoogleApiServiceImpl";

    private final AndroidLauncher androidLauncher;


    public AndroidGoogleApiServiceImpl(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;

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
}
