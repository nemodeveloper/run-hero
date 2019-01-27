package ru.nemodev.runhero.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

/**
 * created by NemoDev on 08.05.2018 - 20:17
 */
public final class ScreenUtils
{
    private ScreenUtils() {}

    public static float getWidth()
    {
        return Gdx.graphics.getWidth();
    }

    public static float getHeight()
    {
        return Gdx.graphics.getHeight();
    }

    public static Vector2 getCenterPoint()
    {
        return new Vector2(getWidth() / 2.f, getHeight() / 2.f);
    }

    public static float getDensity()
    {
        return Gdx.graphics.getDensity();
    }

    public static void clearScreen()
    {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static float getWidthStep(int steps)
    {
        return getWidth() / steps;
    }

    public static float getHeightStep(int steps)
    {
        return getHeight() / steps;
    }

    public static int getFPS()
    {
        return Gdx.graphics.getFramesPerSecond();
    }

}
