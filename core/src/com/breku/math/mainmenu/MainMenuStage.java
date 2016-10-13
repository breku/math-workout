package com.breku.math.mainmenu;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.mainmenu.button.*;
import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 10.10.16.
 */
public class MainMenuStage extends AbstractStage {

    private AbstractMenuButton playButton, achievementButton, exitButton, inboxButton, leaderboardButton, playWithFriendButton;

    public MainMenuStage(GoogleApiService googleApiService) {
        super(googleApiService);
    }

    @Override
    public void initialize() {
        super.initialize();
        playButton = new QuickMatchButton();
        playWithFriendButton = new PlayWithFriendButton();
        inboxButton = new InboxButton();
        achievementButton = new AchievementButton();
        leaderboardButton = new LeaderboardButton();
        exitButton = new ExitButton();

        addActor(playButton);
        addActor(playWithFriendButton);
        addActor(inboxButton);
        addActor(achievementButton);
        addActor(leaderboardButton);
        addActor(exitButton);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (playButton.isClicked()) {
            playButton.setClicked(false);
            googleApiService.launchInvitePlayersScreen();
        }
    }
}
