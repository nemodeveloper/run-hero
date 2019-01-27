package ru.nemodev.runhero.entity.play.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ru.nemodev.runhero.entity.common.BaseActor;

public class TreeActor extends BaseActor
{
    private final Sprite treeSprite;
    private final Vector2 position;

    public TreeActor(Sprite treeSprite, Vector2 position)
    {
        this.treeSprite = treeSprite;
        this.position = position;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.draw(treeSprite, position.x, position.y, treeSprite.getWidth(), treeSprite.getHeight());
    }
}
