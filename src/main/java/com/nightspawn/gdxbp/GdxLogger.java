package com.nightspawn.gdxbp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.StringBuilder;

/**
 * Simple logger, usage:
 * <p>
 * <pre>
 *  ...
 *  private static final GdxLogger LOG = new GdxLogger(MyClass.class);
 *  ...
 *  LOG.info("message one={}, two={}", obj1, obj3);
 *  ...
 * </pre>
 *
 * @author nsn
 */
public class GdxLogger {
    public static final char PLACEHOLDER_START = '{';
    public static final char PLACEHOLDER_END = '}';
    private static final String ARG_MISSING = "[missing: ";
    private final String tag;

    public GdxLogger(String tag) {
        super();
        this.tag = tag;
    }

    public GdxLogger(Class<?> clazz) {
        super();
        this.tag = clazz.getName();
    }

    public String fmt(String format, Object... args) {
        if (args.length < 1) {
            return format;
        }
        StringBuilder sb = new StringBuilder();
        int end = format.indexOf(PLACEHOLDER_START), start = 0;
        int index = 0;
        do {
            String before = format.substring(start, end);
            sb.append(before);
            if (args.length <= index) {
                sb.append(ARG_MISSING);
                sb.append(index);
                sb.append(']');
            } else {
                sb.append(args[index]);
            }
            start = format.indexOf(PLACEHOLDER_END, end) + 1;
            end = format.indexOf(PLACEHOLDER_START, start);
            index++;
        } while (end >= 0);
        sb.append(format.substring(format.lastIndexOf(PLACEHOLDER_END) + 1));
        return sb.toString();
    }

    public void info(String msg, Object... args) {
        Gdx.app.log(tag, fmt(msg, args));
    }

    public void info(String msg, Throwable t) {
        Gdx.app.log(tag, msg, t);
    }

    public void debug(String msg, Object... args) {
        Gdx.app.debug(tag, fmt(msg, args));
    }

    public void debug(String msg, Throwable t) {
        Gdx.app.debug(tag, msg, t);
    }

    public void error(String msg, Object... args) {
        Gdx.app.error(tag, fmt(msg, args));
    }

    public void error(String msg, Throwable t) {
        Gdx.app.error(tag, msg, t);
    }

}
