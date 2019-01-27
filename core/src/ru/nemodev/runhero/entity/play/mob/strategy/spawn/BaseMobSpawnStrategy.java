package ru.nemodev.runhero.entity.play.mob.strategy.spawn;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.FloatArray;

import net.dermetfan.gdx.graphics.g2d.AnimatedBox2DSprite;
import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import java.util.Random;

import ru.nemodev.runhero.entity.play.Box2dBodyType;
import ru.nemodev.runhero.entity.play.ConstantBox2dBodyType;
import ru.nemodev.runhero.entity.play.mob.AnimationMobActor;
import ru.nemodev.runhero.entity.play.mob.BaseMobActor;
import ru.nemodev.runhero.entity.play.mob.StaticMobActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.util.Box2dObjectBuilder;
import ru.nemodev.runhero.util.SpriteUtils;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;


public abstract class BaseMobSpawnStrategy implements MobSpawnStrategy
{
    private final static float Mob_SIZE_STEP = 0.5f;

    protected final World world;

    protected final int maxScore;
    protected final float destinationX;
    protected Vector2 spawnPos;
    protected final boolean enableDynamicMob;

    protected final Random random;
    protected final FloatArray mobSizes;

    private BaseMobActor lastMob;

    public BaseMobSpawnStrategy(World world, int maxScore, float minMobSize, float maxMobSize, float destinationX, Vector2 startSpawnPos, boolean enableDynamicMob)
    {
        this.world = world;
        this.maxScore = maxScore;

        this.mobSizes = new FloatArray();
        float curMobSize = minMobSize;
        while (curMobSize <= maxMobSize)
        {
            this.mobSizes.add(curMobSize / 2.f);
            curMobSize += Mob_SIZE_STEP;
        }

        this.destinationX = destinationX;
        this.spawnPos = new Vector2(startSpawnPos);
        this.enableDynamicMob = enableDynamicMob;

        this.random = new Random();
    }

    public Vector2 getSpawnPos()
    {
        return spawnPos;
    }

    public void setSpawnPos(Vector2 spawnPos)
    {
        this.spawnPos = spawnPos;
    }

    @Override
    public BaseMobActor spawn(Vector3 cameraPos)
    {
        if (isNeedSpawn(cameraPos))
        {
            lastMob = doSpawn();
            return lastMob;
        }
        else
        {
            return null;
        }
    }

    private boolean isNeedSpawn(Vector3 cameraPos)
    {
        // TODO тонкий момент от зависимости METERS_X нужно уходить внтури логики
        return GameManager.getInstance().isRunning()
                && GameManager.getInstance().isRightDirection()
                    ? spawnPos.x + destinationX < cameraPos.x + METERS_X * 2f
                    : spawnPos.x + destinationX > cameraPos.x - METERS_X * 2f;
    }

    protected abstract BaseMobActor doSpawn();

    protected Fixture getCircleFixture(Box2dBodyType bodyType, float radius)
    {
        Fixture circleFixture = Box2dObjectBuilder.createCircleFixture(world, bodyType, spawnPos, radius);
        circleFixture.getBody().setFixedRotation(true);
        spawnPos.add(destinationX, 0.f);

        return circleFixture;
    }

    protected Fixture getPolygonFixture(Box2dBodyType bodyType, float width, float height)
    {
        return getPolygonFixture(bodyType,width, height, spawnPos);
    }

    protected Fixture getPolygonFixture(Box2dBodyType bodyType, float width, float height, Vector2 position)
    {
        Fixture polygonFixture = Box2dObjectBuilder.createPolygonFixture(world, bodyType, position, width, height);
        polygonFixture.getBody().setFixedRotation(true);
        spawnPos.add(destinationX, 0.f);

        return polygonFixture;
    }

    protected Box2DSprite getSprite(String atlas, String name)
    {
        return SpriteUtils.createBox2d(atlas, name);
    }

    protected StaticMobActor getCircleMob(String atlas, String name, Box2dBodyType bodyType)
    {
        final float size = getRandomMobSize();
        Fixture circleFixture = getCircleFixture(bodyType, size);

        return new StaticMobActor(world, circleFixture, getSprite(atlas, name));
    }

    protected StaticMobActor getStaticPolygonMob(String atlas, String name, Box2dBodyType bodyType)
    {
        final float width = getRandomMobSize();
        final float height = getRandomMobSize();

        return getStaticPolygonMob(atlas, name, bodyType, width, height);
    }

    protected StaticMobActor getStaticPolygonMob(String atlas, String name, Box2dBodyType bodyType, float width, float height)
    {
        Fixture polygonFixture = getPolygonFixture(bodyType, width, height);
        return new StaticMobActor(world, polygonFixture, getSprite(atlas, name));
    }

//    protected FlyMobActor getFlyMob(String atlas, String name, float width, float height)
//    {
//        float posY = MathUtils.random(1.5f, 5.f);
//
//        Fixture polygonFixture = getPolygonFixture(width, height, new Vector2(spawnPos.x, posY));
//        return new FlyMobActor(world, polygonFixture.getBody(), getSprite(atlas, name));
//    }

    protected AnimationMobActor getAnimationPolygonMob(String atlasName, Box2dBodyType bodyType, float width, float height)
    {
        AnimatedBox2DSprite animation = SpriteUtils.createAnimationBox2d(atlasName, 0.5f, Animation.PlayMode.LOOP_PINGPONG);
        Fixture fixture = getPolygonFixture(bodyType, width, height);

        return new AnimationMobActor(world, fixture, animation);
    }

    protected float getRandomMobSize()
    {
        return this.mobSizes.random();
    }

    protected Box2dBodyType getJumpBox2BodyType()
    {
        final float density = MathUtils.random(0.5f, 1.5f);
        final float restitution = density < 1.f
                ? MathUtils.random(0.85f, 1.f)
                : MathUtils.random(1.1f, 1.25f);

        return new Box2dBodyType()
        {
            @Override
            public BodyDef.BodyType getBodyType()
            {
                return BodyDef.BodyType.DynamicBody;
            }

            @Override
            public float getDensity()
            {
                return density;
            }

            @Override
            public float getFriction()
            {
                return 0.f;
            }

            @Override
            public float getRestitution()
            {
                return restitution;
            }
        };
    }

    protected StaticMobActor getStaticPolygonMob(String atlas, String[] nameKeys)
    {
        float width = getRandomMobSize();
        return getStaticPolygonMob(
                atlas,
                nameKeys[random.nextInt(nameKeys.length)],
                enableDynamicMob && random.nextBoolean()
                        ? getJumpBox2BodyType()
                        : ConstantBox2dBodyType.STATIC_MOB,
                width, width
        );
    }

    protected AnimationMobActor getAnimationPolygonMob(String[] atlasKeys)
    {
        float size = getRandomMobSize();
        return getAnimationPolygonMob(
                atlasKeys[random.nextInt(atlasKeys.length)],
                enableDynamicMob && random.nextBoolean()
                        ? getJumpBox2BodyType()
                        : ConstantBox2dBodyType.STATIC_MOB,
                size, size);
    }

    @Override
    public boolean isCanSpawn(int condition)
    {
        return maxScore >= condition;
    }
}
