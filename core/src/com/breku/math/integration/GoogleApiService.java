package com.breku.math.integration;

/**
 * Created by brekol on 10.10.16.
 */
public interface GoogleApiService {

    void launchInvitePlayersScreen(GoogleCallback<GameIntegrationCallbackValue> googleCallback);

    void launchQuickGame(GoogleCallback<GameIntegrationCallbackValue> googleCallback);

    void launchInbox(GoogleCallback googleCallback);

    void takeTurn(GoogleCallback<GameIntegrationCallbackValue> googleCallback);

    void takeTurnAsMyself(GoogleCallback<GameIntegrationCallbackValue> googleCallback, boolean incrementTurnCounter);

    void takeTurnAsMyself(GoogleCallback<GameIntegrationCallbackValue> googleCallback);

    void login();

    boolean isSignedIn();

    String getCurrentPlayerName();

}
