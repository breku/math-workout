package com.breku.math.desktop;

import com.badlogic.gdx.Gdx;
import com.breku.math.integration.GameIntegrationCallbackValue;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.integration.GoogleCallback;
import com.breku.math.integration.TakeTurnIntegrationCallbackValue;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by brekol on 10.10.16.
 */
public class DesktopGoogleApiService implements GoogleApiService {

    private static final String TAG = "DesktopGoogleApiService";
    private Timer timer = new Timer();

    @Override
    public void launchInvitePlayersScreen(GoogleCallback<GameIntegrationCallbackValue> googleCallback) {
        Gdx.app.log(TAG, "launchInvitePlayersScreen");
        callOnSuccessWithDelay(googleCallback);
    }

    @Override
    public void launchQuickGame(final GoogleCallback<GameIntegrationCallbackValue> googleCallback) {
        Gdx.app.log(TAG, "launchQuickGame");
        callOnSuccessWithDelay(googleCallback);
    }

    @Override
    public void launchInbox(GoogleCallback googleCallback) {
        Gdx.app.log(TAG, "launchInbox");
        callOnSuccessWithDelay(googleCallback);
    }

    @Override
    public void takeTurn(GoogleCallback<TakeTurnIntegrationCallbackValue> googleCallback) {
        Gdx.app.log(TAG, "takeTurn");
        callOnSuccessWithDelay(googleCallback);
    }

    @Override
    public void takeTurnAsMyself(GoogleCallback<GameIntegrationCallbackValue> googleCallback) {
        Gdx.app.log(TAG, "takeTurnAsMyself");
        callOnSuccessWithDelay(googleCallback);
    }

    @Override
    public void login() {
        Gdx.app.log(TAG, "login");
    }

    @Override
    public boolean isSignedIn() {
        return true;
    }


    @Override
    public String getCurrentPlayerName() {
        return "BREKU";
    }

    private void callOnSuccessWithDelay(final GoogleCallback googleCallback) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                googleCallback.onSucces();
            }
        }, 1000);
    }
}
