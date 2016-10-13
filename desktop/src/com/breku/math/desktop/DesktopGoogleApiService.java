package com.breku.math.desktop;

import com.badlogic.gdx.Gdx;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.googleplay.LaunchCallback;

/**
 * Created by brekol on 10.10.16.
 */
public class DesktopGoogleApiService implements GoogleApiService {

    private static final String TAG = "DesktopGoogleApiService";

    @Override
    public void launchInvitePlayersScreen() {
        Gdx.app.log(TAG, "launchInvitePlayersScreen");
    }

    @Override
    public void launchQuickGame(LaunchCallback launchCallback) {
        Gdx.app.log(TAG, "launchQuickGame");
        launchCallback.onSucces();
    }
}
