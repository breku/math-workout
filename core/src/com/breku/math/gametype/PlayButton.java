package com.breku.math.gametype;

import com.badlogic.gdx.graphics.Texture;
import com.breku.math.button.AbstractButton;
import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 15.10.16.
 */
public class PlayButton extends AbstractButton {

    private final GameType gameType;
    private final LevelDifficulty levelDifficulty;

    public PlayButton(Texture texture, float actorX, float actorY, GameType gameType, LevelDifficulty levelDifficulty) {
        super(texture, actorX, actorY, 0.2f, 0.3f);
        this.gameType = gameType;
        this.levelDifficulty = levelDifficulty;
    }

    public GameType getGameType() {
        return gameType;
    }

    public LevelDifficulty getLevelDifficulty() {
        return levelDifficulty;
    }
}
