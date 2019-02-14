package ru.nemodev.runhero.constant;


import ru.nemodev.runhero.util.ScreenUtils;

public final class GameConstant
{
    public static final float METERS_Y = 9.f;
    public static final float METERS_X = ScreenUtils.getWidth() / ScreenUtils.getHeight() * METERS_Y;

    // TODO все объекты должны быть зависимы от этих размеров иначе на разных экранах различное отображение
    public static final float WORLD_UNIT = 1.f;
}
