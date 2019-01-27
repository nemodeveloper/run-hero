package ru.nemodev.runhero.entity.play;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Pool;

import ru.nemodev.runhero.entity.common.BaseActor;
import ru.nemodev.runhero.entity.play.mob.MobEventListener;
import ru.nemodev.runhero.manager.ConfigManager;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.util.ScreenUtils;

public class ScoreViewActor extends BaseActor implements MobEventListener, Pool.Poolable, ScoreChangeListener
{
    private final BitmapFont font;

    private int gameScore;

    public ScoreViewActor(BitmapFont font)
    {
        gameScore = 0;
        this.font = font;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        float posX = ScreenUtils.getWidth() / 2.f;
        float posY = ScreenUtils.getHeight() / 2.f + ScreenUtils.getHeightStep(10) * 3.f;

        if (GameManager.getInstance().isRunning())
        {
            font.draw(batch, String.valueOf(gameScore), posX, posY);
        }
        else if (GameManager.getInstance().isGameOver())
        {
            font.draw(batch, "Game over!",
                    posX - ScreenUtils.getWidthStep(10), posY);

            font.draw(batch,
                    String.format("\n%s / %s", gameScore, ConfigManager.getInstance().getBestScore()),
                    posX - ScreenUtils.getWidthStep(15), posY);
        }
    }

    @Override
    public void mobKillHero()
    {
        ConfigManager.getInstance().setBestScore(gameScore);
    }

    @Override
    public void mobChange()
    {

    }

    @Override
    public void reset()
    {
        gameScore = 0;
    }

    @Override
    public void scoreChange(int score)
    {
        gameScore = score;
    }
}
