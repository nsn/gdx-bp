package com.nightspawn.gdxbp;

public abstract class GameState {
    private static final GdxLogger LOG = new GdxLogger(GameState.class);

    protected GameState() {
        super();
    }

    public void onEntry() {
        LOG.debug("onEntry: " + getClass().getName());
    }

    public void onExit() {
        LOG.debug("onExit: " + getClass().getName());
    }

    public abstract void update(float deltaTime);

    public abstract void render();

    public abstract void resize(int w, int h);
}
