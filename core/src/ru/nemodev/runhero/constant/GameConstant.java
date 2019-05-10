package ru.nemodev.runhero.constant;


import ru.nemodev.runhero.core.util.ScreenUtils;

public final class GameConstant
{
    private GameConstant() { }

    public static final float METERS_Y = 9.f;
    public static final float METERS_X = ScreenUtils.getWidth() / ScreenUtils.getHeight() * METERS_Y;

    public static final float HALF_X = METERS_X / 2.f;
    public static final float HALF_Y = METERS_Y / 2.f;

    public static final float WORLD_UNIT = 1.f;
}
