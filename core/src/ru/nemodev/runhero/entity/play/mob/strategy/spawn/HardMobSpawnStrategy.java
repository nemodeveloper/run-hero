package ru.nemodev.runhero.entity.play.mob.strategy.spawn;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import ru.nemodev.runhero.constant.texture.MobsAnimationTextureConstant;
import ru.nemodev.runhero.entity.play.mob.BaseMobActor;

public class HardMobSpawnStrategy extends NormalMobSpawnStrategy
{
    private static final String[] ACCESS_ANIMATION_CUBE_ATLAS = {
            MobsAnimationTextureConstant.MOB_CUBE_MIMIC_1_ATLAS,
            MobsAnimationTextureConstant.MOB_CUBE_MIMIC_2_ATLAS,
            MobsAnimationTextureConstant.MOB_CUBE_MIMIC_3_ATLAS,
            MobsAnimationTextureConstant.MOB_CUBE_MIMIC_4_ATLAS
    };

    public HardMobSpawnStrategy(World world, int maxScore, float minMobSize, float maxMobSize, float destinationX, Vector2 startSpawnPos, boolean enableDynamicMob)
    {
        super(world, maxScore, minMobSize, maxMobSize, destinationX, startSpawnPos, enableDynamicMob);
    }

    @Override
    protected BaseMobActor doSpawn()
    {
        if (random.nextInt(100) > 40)
        {
            return getAnimationPolygonMob(ACCESS_ANIMATION_CUBE_ATLAS);
        }
        else
        {
            return super.doSpawn();
        }
    }
}
