package com.nightspawn.gdxbp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * InputProcessor wrapper.
 * 
 * @author nsn
 *
 * @param <T>
 *            InputCommand enum
 */
public abstract class Controls<T extends Enum<T>> implements InputProcessor {
    private static final GdxLogger LOG = new GdxLogger(Controls.class);
    private List<InputCommandListener<T>> commandListeners = new ArrayList<Controls.InputCommandListener<T>>();
    private Map<Integer, T> keyMap = new HashMap<Integer, T>();
    private Set<T> commands = new HashSet<T>();

    public Controls() {
    }

    public void addCommandListener(InputCommandListener<T> l) {
        commandListeners.add(l);
    }

    public void removeCommandListener(InputCommandListener<T> l) {
        commandListeners.remove(l);
    }

    public boolean isActive(T c) {
        return commands.contains(c);
    }

    public void addKey(Integer keyCode, T command) {
        keyMap.put(keyCode, command);
    }

    private void onCommand(T c) {
        commands.add(c);
        for (InputCommandListener<T> l : commandListeners) {
            l.onCommand(c);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        LOG.debug("keydown {} {}", keycode, Keys.toString(keycode));
        if (keyMap.containsKey(keycode)) {
            LOG.debug("tralalaa {}", keyMap.get(keycode));
            onCommand(keyMap.get(keycode));
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keyMap.containsKey(keycode)) {
            commands.remove(keyMap.get(keycode));
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    public interface InputCommandListener<T> {
        public void onCommand(T c);
    }

}
