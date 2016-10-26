package com.nightspawn.gdxbp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public abstract class GameBase extends ApplicationAdapter {
    protected static final GdxLogger LOG = new GdxLogger(GameBase.class);
    protected GameState currentState;
    protected Color clearColor = Color.MAGENTA;

    @Override
    public void create() {
        resume();
    }

    public void setState(GameState newState) {
        if (currentState != null) {
            currentState.onExit();
        }
        newState.onEntry();
        currentState = newState;
    }

    @Override
    public void render() {
        // Sets the clear screen color to clearColor
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (currentState != null) {
            currentState.render();
        }
    }

    @Override
    public void dispose() {
        LOG.debug("dispose");
    }

    @Override
    public void resize(int width, int height) {
        if (currentState != null) {
            currentState.resize(width, height);
        }
    }

    @Override
    public void pause() {
        LOG.debug("pause");
    }

    @Override
    public void resume() {
        LOG.debug("resume");
    }

}
