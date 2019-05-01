package ru.nemodev.runhero.entity.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Pool;

import ru.nemodev.runhero.core.manager.system.ConfigManager;
import ru.nemodev.runhero.core.model.BaseActor;
import ru.nemodev.runhero.core.util.ScreenUtils;
import ru.nemodev.runhero.entity.game.mob.kind.MobEventListener;
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

        final float stepY = ScreenUtils.getHeightStep(10);
        final float shiftY = stepY * 4.f;
        float posY = ScreenUtils.getHeight() / 2.f + shiftY;

        if (GameManager.getInstance().isRunning() || GameManager.getInstance().isPause())
        {
            font.draw(batch, String.valueOf(gameScore), posX, posY);
        }
        else if (GameManager.getInstance().isGameOver())
        {
            GlyphLayout glyphLayout = new GlyphLayout();

            glyphLayout.setText(font, "Tap and Run!");
            posX = (ScreenUtils.getWidth() - glyphLayout.width) / 2.f;
            font.draw(batch, glyphLayout, posX, posY);

            posY -= glyphLayout.height + stepY;
            glyphLayout.setText(font, String.format("Best - %s", ConfigManager.getInstance().getBestScore()));
            posX = (ScreenUtils.getWidth() - glyphLayout.width) / 2.f;
            font.draw(batch, glyphLayout, posX, posY);

            posY -= glyphLayout.height + stepY;
            glyphLayout.setText(font, String.format("Now - %s", gameScore));
            posX = (ScreenUtils.getWidth() - glyphLayout.width) / 2.f;
            font.draw(batch, glyphLayout, posX, posY);
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
