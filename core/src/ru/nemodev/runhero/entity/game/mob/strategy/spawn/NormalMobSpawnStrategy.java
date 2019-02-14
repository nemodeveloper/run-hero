package ru.nemodev.runhero.entity.game.mob.strategy.spawn;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import ru.nemodev.runhero.entity.game.mob.BaseMobActor;

public class NormalMobSpawnStrategy extends EasyMobSpawnStrategy
{
    public NormalMobSpawnStrategy(World world, int maxScore, float minMobSize, float maxMobSize, float destinationX, Vector2 startSpawnPos, boolean enableDynamicMob)
    {
        super(world, maxScore, minMobSize, maxMobSize, destinationX, startSpawnPos, enableDynamicMob);
    }

    @Override
    protected BaseMobActor doSpawn()
    {
        return null;
    }
}
