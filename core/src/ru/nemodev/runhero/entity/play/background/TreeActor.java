package ru.nemodev.runhero.entity.play.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.entity.common.BaseActor;

public class TreeActor extends BaseActor
{
    private final Sprite treeSprite;

    public TreeActor(Sprite treeSprite)
    {
        this.treeSprite = treeSprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        treeSprite.draw(batch);
    }
}
