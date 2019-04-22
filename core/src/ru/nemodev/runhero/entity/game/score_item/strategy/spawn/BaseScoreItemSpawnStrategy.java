package ru.nemodev.runhero.entity.game.score_item.strategy.spawn;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import ru.nemodev.runhero.core.util.Box2dObjectBuilder;
import ru.nemodev.runhero.entity.game.ConstantBox2dBodyType;

public abstract class BaseScoreItemSpawnStrategy implements ScoreItemSpawnStrategy
{
    private final int maxScoreItems;
    protected final World world;

    protected BaseScoreItemSpawnStrategy(World world, int maxScoreItems)
    {
        this.world = world;
        this.maxScoreItems = maxScoreItems;
    }

    @Override
    public boolean isCanSpawn(int playerScore)
    {
        return maxScoreItems > playerScore;
    }

    protected Fixture buildScoreItemFixture(Vector2 itemPosition, float radius)
    {
        Fixture scoreItemFixture = Box2dObjectBuilder.createCircleFixture(this.world,
                ConstantBox2dBodyType.SCORE_ITEM, itemPosition, radius);

        scoreItemFixture.setSensor(true);

        return scoreItemFixture;
    }
}
