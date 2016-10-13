package com.breku.math.screen.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by brekol on 13.10.16.
 */
public class TextureManager {

    private AssetManager assetManager;

    public TextureManager() {
        initialize();
    }

    private void initialize() {
        assetManager = new AssetManager();
        assetManager.setLoader(Texture.class , new TextureLoader(new InternalFileHandleResolver()));
        assetManager.load(TextureType.QUICK_MATCH_BUTTON_TEXTURE.getKey(), Texture.class);
        assetManager.load(TextureType.PLAY_WITH_FRIEND_BUTTON_TEXTURE.getKey(), Texture.class);
        assetManager.load(TextureType.INBOX_BUTTON_TEXTURE.getKey(), Texture.class);
        assetManager.load(TextureType.ACHIEVEMENT_BUTTON_TEXTURE.getKey(), Texture.class);
        assetManager.load(TextureType.LEADERBOARD_BUTTON_TEXTURE.getKey(), Texture.class);
        assetManager.load(TextureType.EXIT_BUTTON_TEXTURE.getKey(), Texture.class);
    }

    public boolean update() {
        return assetManager.update();
    }

    public Texture getTexture(TextureType textureType) {
        return assetManager.get(textureType.getKey());
    }
}
