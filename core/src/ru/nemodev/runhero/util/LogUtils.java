package ru.nemodev.runhero.util;

import com.badlogic.gdx.Gdx;

public final class LogUtils
{
    private LogUtils() { }

    public static void info(String tag, String message)
    {
        Gdx.app.log(tag, message);
    }

}
