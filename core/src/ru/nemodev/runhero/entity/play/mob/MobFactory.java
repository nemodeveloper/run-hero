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

    public MobFactory(World world, Vector2 spawnPos)
    {
        this.world = world;
        this.spawnPos = spawnPos;
        this.mobSpawnStrategies = new Array<BaseMobSpawnStrategy>(6);

        initMobStrategy();
    }

    private void initMobStrategy()
    {
        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;

        mobSpawnStrategies.add(new EasyMobSpawnStrategy(world, 10, 1.5f, 4.f, 18.f * direction, spawnPos, false));
        mobSpawnStrategies.add(new EasyMobSpawnStrategy(world, 20, 1.5f, 5.f, 17.f * direction, spawnPos, true));

        mobSpawnStrategies.add(new NormalMobSpawnStrategy(world, 30, 2.0f, 6.5f, 16.f * direction, spawnPos, false));
        mobSpawnStrategies.add(new NormalMobSpawnStrategy(world, 40, 2.0f, 6.5f, 16.f * direction, spawnPos, true));

        mobSpawnStrategies.add(new HardMobSpawnStrategy(world, 50, 2.5f, 7.f, 16.5f * direction, spawnPos, false));
        mobSpawnStrategies.add(new HardMobSpawnStrategy(world, 60, 2.5f, 7.f, 17.5f * direction, spawnPos, true));

        curSpawnStrategy = mobSpawnStrategies.get(0);
    }

    public void setPlayerScore(int score)
    {
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
        return curSpawnStrategy.spawn(cameraPos);
    }

}
