package ru.nemodev.runhero.entity.game.score_item;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

public class DynamicScoreItemActor extends ScoreItemActor
{
    private final float minY;
    private final float maxY;
    private float directionY;

    public DynamicScoreItemActor(World world, Box2DSprite itemSprite, Fixture scoreItemFixture,
                                 float minY, float maxY)
    {
        super(world, itemSprite, scoreItemFixture);

        this.minY = minY;
        this.maxY = maxY;
        this.directionY = MathUtils.randomBoolean() ? -1.f : 1.f;
    }

    @Override
    protected void doAct(float delta)
    {
        super.doAct(delta);

        if (!needRemove)
        {
            Vector2 curPosition = scoreBody.getPosition();
            scoreBody.setTransform(curPosition.x, curPosition.y + directionY * delta, 0.f);

            curPosition = scoreBody.getPosition();
            if (curPosition.y > maxY || minY > curPosition.y)
            {
                directionY *= -1.f;
            }
        }
    }
}
