package ru.nemodev.runhero.entity.game.mob.strategy.spawn;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.FloatArray;

import net.dermetfan.gdx.graphics.g2d.AnimatedBox2DSprite;

import java.util.Random;

import ru.nemodev.runhero.entity.game.Box2dBodyType;
import ru.nemodev.runhero.entity.game.ConstantBox2dBodyType;
import ru.nemodev.runhero.entity.game.mob.AnimationMobActor;
import ru.nemodev.runhero.entity.game.mob.BaseMobActor;
import ru.nemodev.runhero.entity.game.mob.StaticMobActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.util.Box2dObjectBuilder;
import ru.nemodev.runhero.util.SpriteUtils;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;


public abstract class BaseMobSpawnStrategy implements MobSpawnStrategy
{
    private final static float MOB_SIZE_STEP = 0.5f;

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
            curMobSize += MOB_SIZE_STEP;
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

    public float getDestinationX()
    {
        return destinationX;
    }

    @Override
    public BaseMobActor spawn(Vector3 cameraPos)
    {
        if (isNeedSpawn(cameraPos))
        {
            lastMob = doSpawn();
            spawnPos.add(destinationX, 0.f);

            return lastMob;
        }
        else
        {
            return null;
        }
    }

    private boolean isNeedSpawn(Vector3 cameraPos)
    {
        // TODO тонкий момент от зависимости METERS_X нужно уходить внутри логики
        return GameManager.getInstance().isRunning()
                && GameManager.getInstance().isRightDirection()
                    ? spawnPos.x + destinationX < cameraPos.x + METERS_X * 2f
                    : spawnPos.x + destinationX > cameraPos.x - METERS_X * 2f;
    }

    protected abstract BaseMobActor doSpawn();

    protected Fixture getPolygonFixture(Box2dBodyType bodyType, float width, float height)
    {
        return getPolygonFixture(bodyType,width, height, spawnPos);
    }

    protected Fixture getPolygonFixture(Box2dBodyType bodyType, float width, float height, Vector2 position)
    {
        Fixture polygonFixture = Box2dObjectBuilder.createBoxFixture(world, bodyType, position, width, height);
        polygonFixture.getBody().setFixedRotation(true);
        spawnPos.add(destinationX, 0.f);

        return polygonFixture;
    }

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

    protected StaticMobActor getStaticMob(Fixture fixture, String atlasName, String spriteName)
    {
        return new StaticMobActor(world, fixture, SpriteUtils.createBox2d(atlasName, spriteName));
    }
}
