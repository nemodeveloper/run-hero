package ru.nemodev.runhero.entity.play.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.entity.collision.Contactable;
import ru.nemodev.runhero.entity.common.Box2dActor;
import ru.nemodev.runhero.entity.play.ConstantBox2dBodyType;
import ru.nemodev.runhero.entity.play.mob.MobEventListener;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.util.Box2dObjectBuilder;


/**
 * created by NemoDev on 06.05.2018 - 20:14
 */
public class PlatformActor extends Box2dActor implements MobEventListener
{
    private final float[] spritePosX;
    private final float spritePosY;
    private final Fixture platform;
    private final Vector2 bodySize;
    private final Array<Sprite> platformSprites;
    private final Array<Sprite> enableSprite;

    public PlatformActor(World world, Fixture platform, Array<Sprite> enableSprite, Vector2 bodySize, float spriteSize)
    {
        super(world);

        this.platform = platform;
        this.bodySize = bodySize;

        int spriteCount = MathUtils.ceil(bodySize.x / spriteSize);
        this.spritePosX = new float[spriteCount];
        this.spritePosY = platform.getBody().getPosition().y;

        float startPosXForSprite = platform.getBody().getPosition().x - bodySize.x / 2.f + spriteSize / 2.f;

        for (int i = 0; i < spritePosX.length; ++i)
        {
            spritePosX[i] = startPosXForSprite;
            startPosXForSprite += spriteSize;
        }

        this.platformSprites = new Array<Sprite>(spriteCount);
        this.enableSprite = enableSprite;
        fillPlatformSprites();
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

        updatePlatformSprites(moveX);
    }

    private void updatePlatformSprites(float moveX)
    {
        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;
        for (int i = 0; i < spritePosX.length; ++i)
        {
            spritePosX[i] += moveX * direction;
        }
        fillPlatformSprites();
    }

    private void fillPlatformSprites()
    {
        for (int i = 0; i < spritePosX.length; ++i)
        {
            platformSprites.insert(i, enableSprite.get(MathUtils.random(0, enableSprite.size - 1)));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        if (!isVisibleForPlayer())
            return;

        for (int i = 0; i < spritePosX.length; ++i)
        {
            drawSprite(batch, platformSprites.get(i), spritePosX[i], spritePosY);
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

    public static PlatformActor buildPlatformActor(World world, Vector2 bodyPosition, Vector2 bodySize, Array<Sprite> enableSprite, float spriteSize)
    {
        Fixture fixture = Box2dObjectBuilder.createPolygonFixture(
                world, ConstantBox2dBodyType.GROUND,
                bodyPosition, bodySize.x, bodySize.y);

        return new PlatformActor(world, fixture, enableSprite, bodySize, spriteSize);
    }

    @Override
    public void mobKillHero()
    {
        platform.setFriction(100.f);
    }

    @Override
    public void mobChange()
    {

    }
}
