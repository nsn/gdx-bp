package com.nightspawn.gdxbp;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;

/**
 * Convenience wrapper for {@link AssetManager}.
 * 
 * @author nsn
 *
 */
public class AssetUtil implements Disposable, AssetErrorListener {
    private static final GdxLogger LOG = new GdxLogger(AssetUtil.class);

    private AssetManager manager;

    public AssetUtil(AssetManager manager) {
        this.init(manager);
    }

    public void init(AssetManager manager) {
        this.manager = manager;
        manager.setErrorListener(this);
    }

    public <T> void load(String path, Class<T> type) {
        manager.load(path, type);
    }

    public <T> T get(String path, Class<T> type) {
        return manager.get(path, type);
    }

    public void finishLoading() {
        manager.finishLoading();
    }

    @Override
    public void error(@SuppressWarnings("rawtypes") AssetDescriptor asset, Throwable throwable) {
        LOG.error("Unable to load asset: {}", asset.fileName);
        LOG.error("reason:", throwable);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

}
