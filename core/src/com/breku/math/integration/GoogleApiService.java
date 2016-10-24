package com.breku.math.integration;

/**
 * Created by brekol on 10.10.16.
 */
public interface GoogleApiService {

    void launchInvitePlayersScreen();

    void launchQuickGame(GoogleCallback<GameIntegrationCallbackValue> googleCallback);

    void launchInbox(GoogleCallback googleCallback);

    void takeTurn(GoogleCallback googleCallback);

    void login();

    boolean isSignedIn();

}
