package ru.nemodev.runhero.entity.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Pool;

import ru.nemodev.runhero.core.manager.system.ConfigManager;
import ru.nemodev.runhero.core.model.BaseActor;
import ru.nemodev.runhero.core.util.ScreenUtils;
import ru.nemodev.runhero.entity.game.mob.MobEventListener;
import ru.nemodev.runhero.manager.GameManager;

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
    protected void doDraw(Batch batch, float parentAlpha)
    {
        float posX = ScreenUtils.getWidth() / 2.f;
        float posY = ScreenUtils.getHeight() / 2.f + ScreenUtils.getHeightStep(10) * 4.f;

        if (GameManager.getInstance().isRunning() || GameManager.getInstance().isPause())
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
        setScene(null);
    }

    @Override
    public void scoreChange(int score)
    {
        gameScore = score;
    }
}
