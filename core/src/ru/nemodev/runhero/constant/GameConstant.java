package ru.nemodev.runhero.constant;


import ru.nemodev.runhero.util.ScreenUtils;

public final class GameConstant
{
    private GameConstant() { }

    public static final float METERS_Y = 9.f;
    public static final float METERS_X = ScreenUtils.getWidth() / ScreenUtils.getHeight() * METERS_Y;

    public static final float UNITS_X = 16;
    public static final float WORLD_UNIT = METERS_X / UNITS_X;
}
