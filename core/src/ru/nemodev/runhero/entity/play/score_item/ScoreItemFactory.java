package ru.nemodev.runhero.entity.play.score_item;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.entity.play.score_item.strategy.spawn.ScoreItemSpawnStrategy;
import ru.nemodev.runhero.entity.play.score_item.strategy.spawn.SimpleScoreItemSpawnStrategy;

public class ScoreItemFactory
{
    private final World world;
    private final Array<ScoreItemSpawnStrategy> scoreItemSpawnStrategies;

    private ScoreItemSpawnStrategy curSpawnStrategy;

    public ScoreItemFactory(World world)
    {
        this.world = world;
        this.scoreItemSpawnStrategies = new Array<ScoreItemSpawnStrategy>(3);

        initStrategies();
    }

    private void initStrategies()
    {
        curSpawnStrategy = new SimpleScoreItemSpawnStrategy(world, 3.f, 100);
    }

    public ScoreItemActor spawn(float timeDelta, Vector3 playerPosition)
    {
        return curSpawnStrategy.spawn(timeDelta, playerPosition);
    }

    public void setPlayerScore(int playerScore)
    {
        // TODO стратегия спавна пока что одна
    }

}
