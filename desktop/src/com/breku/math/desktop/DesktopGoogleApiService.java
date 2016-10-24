package com.breku.math.desktop;

import com.badlogic.gdx.Gdx;
import com.breku.math.integration.GameIntegrationCallbackValue;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.integration.GoogleCallback;

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
    public void launchQuickGame(GoogleCallback<GameIntegrationCallbackValue> googleCallback) {
        Gdx.app.log(TAG, "launchQuickGame");
        googleCallback.onSucces();
    }

    @Override
    public void launchInbox(GoogleCallback googleCallback) {
        Gdx.app.log(TAG, "launchInbox");
        googleCallback.onSucces();
    }

    @Override
    public void takeTurn(GoogleCallback googleCallback) {
        Gdx.app.log(TAG, "takeTurn");
        googleCallback.onSucces();
    }

    @Override
    public void login() {
        Gdx.app.log(TAG, "login");
    }

    @Override
    public boolean isSignedIn() {
        return true;
    }
}
