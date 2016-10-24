package com.breku.math.screen.manager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by brekol on 13.10.16.
 */
public enum AssetType {

    /**
     * Main Menu
     */
    QUICK_MATCH_BUTTON_TEXTURE("gfx/menu/play.png"),
    PLAY_WITH_FRIEND_BUTTON_TEXTURE("gfx/menu/play_with_friend.png"),
    INBOX_BUTTON_TEXTURE("gfx/menu/inbox.png"),
    LEADERBOARD_BUTTON_TEXTURE("gfx/menu/leaderboard.png"),
    ACHIEVEMENT_BUTTON_TEXTURE("gfx/menu/achievement.png"),
    EXIT_BUTTON_TEXTURE("gfx/menu/exit.png"),
    LOGIN_LOGOUT_BUTTON_TEXTURE("gfx/menu/login.png"),

    /**
     * Game
     */
    OK_BUTTON_TEXTURE("gfx/game/button_ok.png"),
    NO_BUTTON_TEXTURE("gfx/game/button_no.png"),

    /**
     * COMMON
     */
    BLACKBOARD_TEXTURE("gfx/common/blackboard.jpg"),

    /**
     * Game type
     */
    GAME_TYPE_PLAY("gfx/gametype/play.png"),


    /**
     * Fonts
     */
    COMIC_SANS_FONT("fonts/comic.fnt", BitmapFont.class),
    COMIC_SANS_FONT_BIG("fonts/comic-s68.fnt", BitmapFont.class);


    private final String key;
    private final Class clazz;

    AssetType(final String key) {
        this(key, Texture.class);
    }

    AssetType(final String key, final Class clazz) {
        this.key = key;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getKey() {
        return key;
    }
}
