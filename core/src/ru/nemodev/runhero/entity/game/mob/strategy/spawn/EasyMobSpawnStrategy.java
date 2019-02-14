package ru.nemodev.runhero.entity.game.mob.strategy.spawn;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import ru.nemodev.runhero.constant.physic.MobPhysicConstant;
import ru.nemodev.runhero.constant.physic.PhysicLoaderConstant;
import ru.nemodev.runhero.constant.texture.MobsStaticTextureConstant;
import ru.nemodev.runhero.entity.game.mob.BaseMobActor;
import ru.nemodev.runhero.util.Box2dObjectBuilder;


public class EasyMobSpawnStrategy extends BaseMobSpawnStrategy
{
    private static final String[] STONE_MOB_ATLAS = {
            MobsStaticTextureConstant.STONE_SMALL_MOB_ATLAS,
            MobsStaticTextureConstant.STONE_MEDIUM_MOB_ATLAS,
            MobsStaticTextureConstant.STONE_BIG_MOB_ATLAS,
            MobsStaticTextureConstant.STONE_COMPOSITE_MOB_ATLAS
    };

    public EasyMobSpawnStrategy(World world, int maxScore, float minMobSize, float maxMobSize, float destinationX, Vector2 startSpawnPos, boolean enableDynamicMob)
    {
        super(world, maxScore, minMobSize, maxMobSize, destinationX, startSpawnPos, enableDynamicMob);
    }

    @Override
    protected BaseMobActor doSpawn()
    {
        int stoneTypeIndex = 3;//random.nextInt(MobPhysicConstant.STONE_MOBS.length);

        String bodyName = MobPhysicConstant.STONE_MOBS[stoneTypeIndex];
        String spriteName = MobsStaticTextureConstant.STONE_MOB_NAMES[0];//random.nextInt(MobsStaticTextureConstant.STONE_MOB_NAMES.length)];

        return getStaticMob(
                Box2dObjectBuilder.createFixture(
                        world.createBody(Box2dObjectBuilder.getBodyDef(BodyDef.BodyType.DynamicBody, spawnPos)),
                        Box2dObjectBuilder.getFixtureDef(10.f, 10.f, 0.f),
                        MathUtils.random(1.5f, 3.5f),
                        PhysicLoaderConstant.STONE_MOBS, bodyName),
                STONE_MOB_ATLAS[stoneTypeIndex], spriteName );
    }
}
