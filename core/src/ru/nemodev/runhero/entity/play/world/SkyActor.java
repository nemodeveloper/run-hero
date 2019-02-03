package ru.nemodev.runhero.entity.play.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.entity.collision.Contactable;
import ru.nemodev.runhero.entity.common.Box2dActor;
import ru.nemodev.runhero.entity.play.ConstantBox2dBodyType;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.util.Box2dObjectBuilder;


/**
 * created by NemoDev on 06.05.2018 - 20:14
 */
public class SkyActor extends Box2dActor
{
    private final float[] spritePosX;
    private final float spritePosY;
    private final Fixture platform;
    private final Vector2 bodySize;
    private final Array<Sprite> skySprites;

    private final Array<Sprite> enableSkySprites;

    public static SkyActor buildSkyActor(World world, Vector2 bodyPosition, Vector2 bodySize, Array<Sprite> enableSkySprites, float spriteSize)
    {
        Fixture fixture = Box2dObjectBuilder.createPolygonFixture(
                world, ConstantBox2dBodyType.GROUND,
                bodyPosition, bodySize.x, bodySize.y);

        return new SkyActor(world, fixture, enableSkySprites, bodySize, spriteSize);
    }

    public SkyActor(World world, Fixture platform, Array<Sprite> enableSkySprites, Vector2 bodySize, float spriteSize)
    {
        super(world);

        this.platform = platform;
        this.bodySize = bodySize;

        int spriteCount = MathUtils.ceil(bodySize.x / spriteSize);
        this.spritePosX = new float[spriteCount];
        this.spritePosY = GameConstant.METERS_Y - 0.1f;

        float startPosXForSprite = platform.getBody().getPosition().x - bodySize.x / 2.f;

        for (int i = 0; i < spritePosX.length; ++i)
        {
            spritePosX[i] = startPosXForSprite;
            startPosXForSprite += spriteSize;
        }

        this.skySprites = new Array<Sprite>(spriteCount);
        this.enableSkySprites = enableSkySprites;

        generateSkySprites();
    }

    public void setContactable(Contactable contactable)
    {
        this.platform.setUserData(contactable);
        this.platform.getBody().setUserData(contactable);
    }

    public boolean isVisibleForPlayer()
    {
        Vector2 groundPlatformPos = platform.getBody().getPosition();
        float cameraPosX = getStage().getCamera().position.x;
        return GameManager.getInstance().isRightDirection()
                ? groundPlatformPos.x + bodySize.x > cameraPosX
                : groundPlatformPos.x - bodySize.x < cameraPosX;
    }

    public void movePlatform(float moveX)
    {
        Vector2 groundPlatformPos = platform.getBody().getPosition();
        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;
        platform.getBody().setTransform(groundPlatformPos.set(groundPlatformPos.x + moveX * direction, groundPlatformPos.y), 0.f);

        updateSkySprites(moveX);
    }

    private void updateSkySprites(float moveX)
    {
        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;
        for (int i = 0; i < spritePosX.length; ++i)
        {
            spritePosX[i] += moveX * direction;
        }
        generateSkySprites();
    }

    private void generateSkySprites()
    {
        for (int i = 0; i < spritePosX.length; ++i)
        {
            skySprites.add(enableSkySprites.get(MathUtils.random(0, enableSkySprites.size - 1)));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        if (!isVisibleForPlayer())
            return;

        for (int i = 0; i < spritePosX.length; ++i)
        {
            drawSprite(batch, skySprites.get(i), spritePosX[i], spritePosY);
        }
    }

    @Override
    public boolean isTouchable()
    {
        return false;
    }

    @Override
    public Actor hit(float x, float y, boolean touchable)
    {
        return null;
    }

}
