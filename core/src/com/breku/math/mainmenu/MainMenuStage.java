package com.breku.math.mainmenu;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.googleplay.LaunchCallback;
import com.breku.math.googleplay.QuickMatchCallback;
import com.breku.math.mainmenu.button.*;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import static com.breku.math.screen.manager.AssetType.*;

/**
 * Created by brekol on 10.10.16.
 */
public class MainMenuStage extends AbstractStage {

    private AbstractMenuButton quickMatchButton, achievementButton, exitButton, inboxButton, leaderboardButton, playWithFriendButton;

    public MainMenuStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        quickMatchButton = new QuickMatchButton(assetManagerWrapper.getTexture(QUICK_MATCH_BUTTON_TEXTURE));
        playWithFriendButton = new PlayWithFriendButton(assetManagerWrapper.getTexture(PLAY_WITH_FRIEND_BUTTON_TEXTURE), googleApiService);
        inboxButton = new InboxButton(assetManagerWrapper.getTexture(INBOX_BUTTON_TEXTURE));
        achievementButton = new AchievementButton(assetManagerWrapper.getTexture(ACHIEVEMENT_BUTTON_TEXTURE));
        leaderboardButton = new LeaderboardButton(assetManagerWrapper.getTexture(LEADERBOARD_BUTTON_TEXTURE));
        exitButton = new ExitButton(assetManagerWrapper.getTexture(EXIT_BUTTON_TEXTURE));

        addActor(quickMatchButton);
        addActor(playWithFriendButton);
        addActor(inboxButton);
        addActor(achievementButton);
        addActor(leaderboardButton);
        addActor(exitButton);
    }

    @Override
    public void disposeStage() {
        quickMatchButton.remove();
        playWithFriendButton.remove();
        inboxButton.remove();
        achievementButton.remove();
        leaderboardButton.remove();
        exitButton.remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (quickMatchButton.isClicked()) {
            quickMatchButton.setClicked(false);
            googleApiService.launchQuickGame(new QuickMatchCallback(this));
        }

        if (inboxButton.isClicked()) {
            inboxButton.setClicked(false);
            googleApiService.launchInbox(new LaunchCallback() {
                @Override
                public void onSucces() {

                }

                @Override
                public void onFailure() {

                }
            });
        }

    }
}
