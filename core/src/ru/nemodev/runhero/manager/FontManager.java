package ru.nemodev.runhero.manager;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;

import ru.nemodev.runhero.util.ScreenUtils;

/**
 * created by NemoDev on 08.05.2018 - 20:45
 */
public final class FontManager implements Disposable
{
    private static volatile FontManager instance = new FontManager();

//    private final BitmapFont box2dCommonFont;
    private BitmapFont commonFont;

    private FontManager()
    {
//        box2dCommonFont = generateFont((int) ((1.f / 9.f) * ScreenUtils.getWidth()));
//        box2dCommonFont.getData().setScale(METERS_X / ScreenUtils.getWidth(), METERS_Y / ScreenUtils.getHeight());
        init();
    }

    private void init()
    {
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/SourceSansPro-Regular.ttf"));
        commonFont = generateFont(freeTypeFontGenerator, 32);

        freeTypeFontGenerator.dispose();
    }

    public static FontManager getInstance()
    {
        return instance;
    }

    public BitmapFont getCommonFont()
    {
        return commonFont;
    }

    @Override
    public void dispose()
    {
        commonFont.dispose();
    }

    private BitmapFont generateFont(FreeTypeFontGenerator freeTypeFontGenerator, int size)
    {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = MathUtils.ceil(size * ScreenUtils.getDensity());
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;

        BitmapFont font = freeTypeFontGenerator.generateFont(parameter);
        font.setColor(Color.WHITE);

        return font;
    }
}
