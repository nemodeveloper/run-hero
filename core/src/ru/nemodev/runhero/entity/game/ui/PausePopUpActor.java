package ru.nemodev.runhero.entity.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.entity.common.BaseActor;

public class PausePopUpActor extends BaseActor
{
    private final Sprite backgroundSprite;

    public PausePopUpActor(Sprite backgroundSprite)
    {
        super();
        this.backgroundSprite = backgroundSprite;
        setVisible(false);
    }

    @Override
    protected void doDraw(Batch batch, float parentAlpha)
    {
        backgroundSprite.draw(batch);
    }
}
