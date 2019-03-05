package ru.nemodev.runhero.util;

import com.badlogic.gdx.Gdx;

public final class LogUtils
{
    private LogUtils() { }

    public static void info(String tag, String message)
    {
        Gdx.app.log(tag, message);
    }

    public static void error(String tag, String message, Throwable exception)
    {
        Gdx.app.error(tag, message, exception);
    }
}
