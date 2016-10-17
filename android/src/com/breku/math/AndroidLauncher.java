package com.breku.math;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.multiplayer.result.ActivityResultService;
import com.breku.math.multiplayer.AndroidGoogleApiServiceImpl;
import com.breku.math.multiplayer.MatchService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements GameHelper.GameHelperListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "AndroidLauncher";
    private GameHelper gameHelper;

    private GoogleApiService googleApiService;
    private ActivityResultService activityResultService;
    private MatchService matchService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.numSamples = 2;
        matchService = new MatchService(this);
        googleApiService = new AndroidGoogleApiServiceImpl(this,matchService);
        activityResultService = new ActivityResultService(this, matchService);
        initialize(new MyGdxGame(googleApiService), config);

        if (gameHelper == null) {
            gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
            gameHelper.enableDebugLog(true);
        }
        gameHelper.setup(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        gameHelper.onStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        gameHelper.onStop();
    }

    public GameHelper getGameHelper() {
        return gameHelper;
    }

    @Override
    public void onActivityResult(int request, int response, Intent data) {
        super.onActivityResult(request, response, data);
        gameHelper.onActivityResult(request, response, data);
        activityResultService.onActivityResult(request, response, data);
    }


    @Override
    public void onSignInFailed() {
        Log.e(TAG, "onSignInFailed");
    }

    @Override
    public void onSignInSucceeded() {
        Log.i(TAG, "onSignInSucceeded");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed");

    }
}
