package ru.nemodev.runhero.entity.game.score_item.strategy.spawn;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.ScoreItemTextureConstant;
import ru.nemodev.runhero.entity.game.score_item.ScoreItemActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.util.SpriteUtils;

public class SimpleScoreItemSpawnStrategy extends BaseScoreItemSpawnStrategy
{
    private final float spawnTime;
    private float curTime = 0;

    public SimpleScoreItemSpawnStrategy(World world, float spawnTime, int maxScoreItems)
    {
        super(world, maxScoreItems);
        this.spawnTime = spawnTime;
    }

    @Override
    public ScoreItemActor<Box2DSprite> spawn(float timeDelta, Vector3 playerPosition)
    {
        curTime += timeDelta;
        if (curTime >= spawnTime)
        {
            curTime = 0;

            float deltaX = GameManager.getInstance().isRightDirection()
                    ? GameConstant.METERS_X / 2
                    : -GameConstant.METERS_X / 2;

            Vector2 itemPosition = new Vector2(
                    playerPosition.x + deltaX,
                    MathUtils.random(2.f, GameConstant.METERS_Y - 2.f));

            return new ScoreItemActor<Box2DSprite>(this.world,
                    SpriteUtils.createBox2d(ScoreItemTextureConstant.SCORE_ITEM_ATLAS, ScoreItemTextureConstant.STAR_ITEM)
                    , buildScoreItemFixture(itemPosition, 1.5f));
        }

        return null;
    }
}
