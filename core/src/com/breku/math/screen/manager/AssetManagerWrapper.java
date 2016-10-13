package com.breku.math.screen.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.Locale;

/**
 * Created by brekol on 13.10.16.
 */
public class AssetManagerWrapper {

    private static final String TAG = "AssetManagerWrapper";
    private AssetManager assetManager;
    private boolean finishedLoading = false;

    public AssetManagerWrapper() {
        initialize();
    }

    private void initialize() {
        assetManager = new AssetManager();
        assetManager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));
        assetManager.setLoader(BitmapFont.class, ".ttf", new BitmapFontLoader(new InternalFileHandleResolver()));

        for (final AssetType assetType : AssetType.values()) {
            assetManager.load(assetType.getKey(), assetType.getClazz());
        }
    }

    public boolean update() {
        if (!finishedLoading) {
            Gdx.app.debug(TAG, String.format(Locale.ENGLISH, "Loading assets progress=[%.2f%%]", assetManager.getProgress()));
            final boolean update = assetManager.update();
            finishedLoading |= update;
        }
        return finishedLoading;
    }

    public Texture getTexture(AssetType assetType) {
        return assetManager.get(assetType.getKey());
    }

    public BitmapFont getFont(AssetType assetType) {
        return assetManager.get(assetType.getKey());
    }
}
