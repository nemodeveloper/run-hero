package ru.nemodev.runhero.entity.game.mob;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.entity.game.mob.strategy.spawn.BaseMobSpawnStrategy;
import ru.nemodev.runhero.entity.game.mob.strategy.spawn.StoneMobSpawnStrategy;
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

        mobSpawnStrategies.add(new StoneMobSpawnStrategy(world, 15,
                2.f, GameConstant.WORLD_UNIT * 4.f,
                GameConstant.WORLD_UNIT * 20 * direction, spawnPos, false));

        mobSpawnStrategies.add(new StoneMobSpawnStrategy(world, 30,
                2.5f, GameConstant.WORLD_UNIT * 5.f,
                GameConstant.WORLD_UNIT * 18.f * direction, spawnPos, true));

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
