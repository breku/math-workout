package com.breku.math.desktop;

import com.breku.math.googleplay.GoogleApiService;
import org.lwjgl.Sys;

/**
 * Created by brekol on 10.10.16.
 */
public class DesktopGoogleApiService implements GoogleApiService {

    private static final String TAG = "DesktopGoogleApiService";

    @Override
    public void launchInvitePlayersScreen() {
        System.out.println("launchInvitePlayersScreen");
    }
}
