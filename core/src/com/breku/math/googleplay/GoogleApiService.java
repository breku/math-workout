package com.breku.math.googleplay;

/**
 * Created by brekol on 10.10.16.
 */
public interface GoogleApiService {

    void launchInvitePlayersScreen();

    void launchQuickGame(LaunchCallback launchCallback);

    void launchInbox(LaunchCallback launchCallback);

}
