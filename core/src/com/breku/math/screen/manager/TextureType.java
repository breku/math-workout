package com.breku.math.screen.manager;

/**
 * Created by brekol on 13.10.16.
 */
public enum TextureType {

    QUICK_MATCH_BUTTON_TEXTURE("gfx/menu/play.png"),
    PLAY_WITH_FRIEND_BUTTON_TEXTURE("gfx/menu/play_with_friend.png"),
    INBOX_BUTTON_TEXTURE("gfx/menu/inbox.png"),
    LEADERBOARD_BUTTON_TEXTURE("gfx/menu/leaderboard.png"),
    ACHIEVEMENT_BUTTON_TEXTURE("gfx/menu/achievement.png"),
    EXIT_BUTTON_TEXTURE("gfx/menu/exit.png");


    private final String key;

    TextureType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
