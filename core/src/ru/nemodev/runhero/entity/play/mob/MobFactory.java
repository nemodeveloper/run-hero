package ru.nemodev.runhero.entity.play.mob;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.entity.play.mob.strategy.spawn.BaseMobSpawnStrategy;
import ru.nemodev.runhero.entity.play.mob.strategy.spawn.EasyMobSpawnStrategy;
import ru.nemodev.runhero.entity.play.mob.strategy.spawn.HardMobSpawnStrategy;
import ru.nemodev.runhero.entity.play.mob.strategy.spawn.NormalMobSpawnStrategy;
import ru.nemodev.runhero.manager.GameManager;


public class MobFactory
{
    private final World world;
    private final Vector2 spawnPos;
    private final Array<BaseMobSpawnStrategy> mobSpawnStrategies;
    private BaseMobSpawnStrategy curSpawnStrategy;

    private int playerScore;

    public MobFactory(World world, Vector2 spawnPos)
    {
        this.world = world;
        this.spawnPos = spawnPos;
        this.mobSpawnStrategies = new Array<BaseMobSpawnStrategy>(6);
        this.playerScore = 0;

        initMobStrategy();
    }

    private void initMobStrategy()
    {
        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;

        mobSpawnStrategies.add(new EasyMobSpawnStrategy(world, 15, 2.f, 4.f, 22.f * direction, spawnPos, false));
        mobSpawnStrategies.add(new EasyMobSpawnStrategy(world, 30, 2.5f, 5.f, 20.f * direction, spawnPos, true));

        mobSpawnStrategies.add(new NormalMobSpawnStrategy(world, 45, 2.0f, 6.5f, 20.f * direction, spawnPos, false));
        mobSpawnStrategies.add(new NormalMobSpawnStrategy(world, 60, 2.0f, 6.5f, 18.f * direction, spawnPos, true));

        mobSpawnStrategies.add(new HardMobSpawnStrategy(world, 75, 2.5f, 7.f, 18.f * direction, spawnPos, false));
        mobSpawnStrategies.add(new HardMobSpawnStrategy(world, 100, 2.5f, 7.f, 17.f * direction, spawnPos, true));

        curSpawnStrategy = mobSpawnStrategies.get(0);
    }

    public void setPlayerScore(int score)
    {
        playerScore = score;

        if (curSpawnStrategy.isCanSpawn(score))
            return;
        else
            mobSpawnStrategies.removeValue(curSpawnStrategy, true);

        for (BaseMobSpawnStrategy mobSpawnStrategy : mobSpawnStrategies)
        {
            if (mobSpawnStrategy.isCanSpawn(score))
            {
                Vector2 lastSpawnPos = curSpawnStrategy.getSpawnPos();
                curSpawnStrategy = mobSpawnStrategy;
                curSpawnStrategy.setSpawnPos(lastSpawnPos);
                for (MobEventListener mobEventListener : GameManager.getInstance().getMobEventListeners())
                {
                    mobEventListener.mobChange();
                }
                return;
            }
        }
    }

    public BaseMobActor getMob(Vector3 cameraPos)
    {
        if (playerScore < 3)
        {
            curSpawnStrategy.getSpawnPos().x = cameraPos.x + curSpawnStrategy.getDestinationX();
            return null;
        }

        return curSpawnStrategy.spawn(cameraPos);
    }

}
