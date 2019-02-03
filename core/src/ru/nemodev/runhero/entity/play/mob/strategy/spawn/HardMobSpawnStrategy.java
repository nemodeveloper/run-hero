package ru.nemodev.runhero.entity.play.mob.strategy.spawn;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import ru.nemodev.runhero.entity.play.mob.BaseMobActor;

public class HardMobSpawnStrategy extends NormalMobSpawnStrategy
{
    public HardMobSpawnStrategy(World world, int maxScore, float minMobSize, float maxMobSize, float destinationX, Vector2 startSpawnPos, boolean enableDynamicMob)
    {
        super(world, maxScore, minMobSize, maxMobSize, destinationX, startSpawnPos, enableDynamicMob);
    }

    @Override
    protected BaseMobActor doSpawn()
    {
        return null;
    }
}
