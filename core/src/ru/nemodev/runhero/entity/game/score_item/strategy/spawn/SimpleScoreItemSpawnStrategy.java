package ru.nemodev.runhero.entity.game.score_item.strategy.spawn;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.ScoreItemTextureConstant;
import ru.nemodev.runhero.core.util.SpriteUtils;
import ru.nemodev.runhero.entity.game.score_item.DynamicScoreItemActor;
import ru.nemodev.runhero.entity.game.score_item.ScoreItemActor;
import ru.nemodev.runhero.manager.GameManager;

public class SimpleScoreItemSpawnStrategy extends BaseScoreItemSpawnStrategy
{
    private final float spawnTime;
    private float curTime = 0;
    private float nextSpawnTime;

    public SimpleScoreItemSpawnStrategy(World world, float spawnTime, int maxScoreItems)
    {
        super(world, maxScoreItems);
        this.spawnTime = spawnTime;
        this.nextSpawnTime = spawnTime;
    }

    @Override
    public ScoreItemActor spawn(float timeDelta, Vector3 playerPosition)
    {
        curTime += timeDelta;
        if (curTime >= nextSpawnTime)
        {
            curTime = 0;
            nextSpawnTime = MathUtils.random(spawnTime - 1.f, spawnTime + 2.f);

            float deltaX = GameManager.getInstance().isRightDirection()
                    ? GameConstant.METERS_X / 2
                    : -GameConstant.METERS_X / 2;

            float minPosY = 2.f;
            float maxPosY = GameConstant.METERS_Y - 1.f;

            Vector2 itemPosition = new Vector2(
                    playerPosition.x + deltaX,
                    MathUtils.random(minPosY, maxPosY));

            String scoreItemName = ScoreItemTextureConstant.SCORE_ITEMS[
                    MathUtils.random(0, ScoreItemTextureConstant.SCORE_ITEMS.length - 1)];

            Box2DSprite sprite = SpriteUtils.createBox2d(ScoreItemTextureConstant.SCORE_ITEM_ATLAS, scoreItemName);
            Fixture fixture = buildScoreItemFixture(itemPosition, 1.5f);

            if (MathUtils.random(1.f) < 0.7f)
            {
                return new ScoreItemActor(world, sprite, fixture);
            }
            else
            {
                return new DynamicScoreItemActor(this.world, sprite, fixture,
                        MathUtils.random(minPosY, itemPosition.y),
                        MathUtils.random(itemPosition.y, maxPosY));
            }
        }

        return null;
    }
}
