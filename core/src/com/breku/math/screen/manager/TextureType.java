package com.breku.math.screen.manager;

/**
 * Created by brekol on 13.10.16.
 */
public enum TextureType {

    /**
     * Main Menu
     */
    QUICK_MATCH_BUTTON_TEXTURE("gfx/menu/play.png"),
    PLAY_WITH_FRIEND_BUTTON_TEXTURE("gfx/menu/play_with_friend.png"),
    INBOX_BUTTON_TEXTURE("gfx/menu/inbox.png"),
    LEADERBOARD_BUTTON_TEXTURE("gfx/menu/leaderboard.png"),
    ACHIEVEMENT_BUTTON_TEXTURE("gfx/menu/achievement.png"),
    EXIT_BUTTON_TEXTURE("gfx/menu/exit.png"),

    /**
     * Game
     */
    OK_BUTTON_TEXTURE("gfx/game/button_ok.png"),
    NO_BUTTON_TEXTURE("gfx/game/button_no.png"),

    /**
     * COMMON
     */
    BLACKBOARD_TEXTURE("gfx/common/blackboard.jpg");


    private final String key;

    TextureType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
