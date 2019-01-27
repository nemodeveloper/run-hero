package ru.nemodev.runhero.entity.play.mob.strategy.spawn;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import ru.nemodev.runhero.constant.texture.MobsAnimationTextureConstant;
import ru.nemodev.runhero.constant.texture.MobsStaticTextureConstant;
import ru.nemodev.runhero.entity.play.mob.BaseMobActor;


public class EasyMobSpawnStrategy extends BaseMobSpawnStrategy
{
    private static final String[] ACCESS_STATIC_CUBE_ATLAS = {
            MobsStaticTextureConstant.MOB_CUBE_1_ATLAS
    };

    private static final String[] ACCESS_ANIMATION_CUBE_ATLAS = {
            MobsAnimationTextureConstant.MOB_CUBE_AROUND_1_ATLAS,
            MobsAnimationTextureConstant.MOB_CUBE_AROUND_2_ATLAS,
            MobsAnimationTextureConstant.MOB_CUBE_AROUND_3_ATLAS
    };

    private static final String[] STATIC_CUBE_KEYS = MobsStaticTextureConstant.MOB_CUBE_KEYS;


    public EasyMobSpawnStrategy(World world, int maxScore, float minMobSize, float maxMobSize, float destinationX, Vector2 startSpawnPos, boolean enableDynamicMob)
    {
        super(world, maxScore, minMobSize, maxMobSize, destinationX, startSpawnPos, enableDynamicMob);
    }

    @Override
    protected BaseMobActor doSpawn()
    {
        if (random.nextBoolean())
            return getAnimationPolygonMob(ACCESS_ANIMATION_CUBE_ATLAS);

        return getStaticPolygonMob(
                ACCESS_STATIC_CUBE_ATLAS[random.nextInt(ACCESS_STATIC_CUBE_ATLAS.length)],
                STATIC_CUBE_KEYS);
    }
}
