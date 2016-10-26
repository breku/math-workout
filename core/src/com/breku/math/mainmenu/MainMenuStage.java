package com.breku.math.mainmenu;

import com.breku.math.configuration.ContextConstants;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.integration.LoadingFinishedCallback;
import com.breku.math.mainmenu.button.*;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import static com.breku.math.screen.manager.AssetType.*;

/**
 * Created by brekol on 10.10.16.
 */
public class MainMenuStage extends AbstractStage {

    private AbstractMenuButton quickMatchButton, achievementButton, exitButton, inboxButton, leaderboardButton, playWithFriendButton, loginLogoutButton;
    private String currentPlayerName;

    public MainMenuStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        currentPlayerName = googleApiService.getCurrentPlayerName();
        quickMatchButton = new QuickMatchButton(assetManagerWrapper.getTexture(QUICK_MATCH_BUTTON_TEXTURE));
        playWithFriendButton = new PlayWithFriendButton(assetManagerWrapper.getTexture(PLAY_WITH_FRIEND_BUTTON_TEXTURE));
        inboxButton = new InboxButton(assetManagerWrapper.getTexture(INBOX_BUTTON_TEXTURE));
        achievementButton = new AchievementButton(assetManagerWrapper.getTexture(ACHIEVEMENT_BUTTON_TEXTURE));
        leaderboardButton = new LeaderboardButton(assetManagerWrapper.getTexture(LEADERBOARD_BUTTON_TEXTURE));
        exitButton = new ExitButton(assetManagerWrapper.getTexture(EXIT_BUTTON_TEXTURE));
        loginLogoutButton = new LoginLogoutButton(assetManagerWrapper.getTexture(LOGIN_LOGOUT_BUTTON_TEXTURE));
        addActor(quickMatchButton);
        addActor(playWithFriendButton);
        addActor(inboxButton);
        addActor(achievementButton);
        addActor(leaderboardButton);
        addActor(exitButton);
        addActor(loginLogoutButton);
    }

    @Override
    public void disposeStage() {
        super.disposeStage();
        quickMatchButton.remove();
        playWithFriendButton.remove();
        inboxButton.remove();
        achievementButton.remove();
        leaderboardButton.remove();
        exitButton.remove();
    }

    @Override
    public void draw() {
        super.draw();

        getBatch().begin();
        font.draw(getBatch(), currentPlayerName, 800, 500);
        getBatch().end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (quickMatchButton.isClicked()) {
            quickMatchButton.setClicked(false);
            addAdditionalData(ContextConstants.ADDITIONAL_DATA_QUICKMATCH, true);
            googleApiService.launchQuickGame(new LoadingFinishedCallback(this, null));
            setTargetScreenType(ScreenType.LOADING);
        }

        if (inboxButton.isClicked()) {
            inboxButton.setClicked(false);
            googleApiService.launchInbox(new LoadingFinishedCallback(this, null));
        }


        if (playWithFriendButton.isClicked()) {
            playWithFriendButton.setClicked(false);
            addAdditionalData(ContextConstants.ADDITIONAL_DATA_SELECT_PLAYERS, true);
            setTargetScreenType(ScreenType.GAME_TYPE);
        }

        if (loginLogoutButton.isClicked()) {
            loginLogoutButton.setClicked(false);
            googleApiService.login();
        }

    }
}
