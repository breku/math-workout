package com.breku.math.mainmenu;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.googleplay.QuickMatchCallback;
import com.breku.math.mainmenu.button.*;
import com.breku.math.screen.manager.TextureManager;
import com.breku.math.stage.AbstractStage;

import static com.breku.math.screen.manager.TextureType.*;

/**
 * Created by brekol on 10.10.16.
 */
public class MainMenuStage extends AbstractStage {

    private AbstractMenuButton quickMatchButton, achievementButton, exitButton, inboxButton, leaderboardButton, playWithFriendButton;

    public MainMenuStage(GoogleApiService googleApiService, TextureManager textureManager) {
        super(googleApiService, textureManager);
    }

    @Override
    public void initialize() {
        super.initialize();
        quickMatchButton = new QuickMatchButton(textureManager.getTexture(QUICK_MATCH_BUTTON_TEXTURE));
        playWithFriendButton = new PlayWithFriendButton(textureManager.getTexture(PLAY_WITH_FRIEND_BUTTON_TEXTURE), googleApiService);
        inboxButton = new InboxButton(textureManager.getTexture(INBOX_BUTTON_TEXTURE));
        achievementButton = new AchievementButton(textureManager.getTexture(ACHIEVEMENT_BUTTON_TEXTURE));
        leaderboardButton = new LeaderboardButton(textureManager.getTexture(LEADERBOARD_BUTTON_TEXTURE));
        exitButton = new ExitButton(textureManager.getTexture(EXIT_BUTTON_TEXTURE));

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

    }
}
