package com.breku.math.mainmenu;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.googleplay.QuickMatchCallback;
import com.breku.math.mainmenu.button.*;
import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 10.10.16.
 */
public class MainMenuStage extends AbstractStage {

    private AbstractMenuButton quickMatchButton, achievementButton, exitButton, inboxButton, leaderboardButton, playWithFriendButton;

    public MainMenuStage(GoogleApiService googleApiService) {
        super(googleApiService);
    }

    @Override
    public void initialize() {
        super.initialize();
        quickMatchButton = new QuickMatchButton();
        playWithFriendButton = new PlayWithFriendButton(googleApiService);
        inboxButton = new InboxButton();
        achievementButton = new AchievementButton();
        leaderboardButton = new LeaderboardButton();
        exitButton = new ExitButton();

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
