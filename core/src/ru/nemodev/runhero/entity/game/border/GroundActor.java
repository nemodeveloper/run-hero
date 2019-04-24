package ru.nemodev.runhero.entity.game.border;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.core.model.Box2dActor;
import ru.nemodev.runhero.core.physic.collision.Contactable;
import ru.nemodev.runhero.core.util.Box2dObjectBuilder;
import ru.nemodev.runhero.entity.game.ConstantBox2dBodyType;
import ru.nemodev.runhero.entity.game.mob.kind.MobEventListener;
import ru.nemodev.runhero.manager.GameManager;


/**
 * created by NemoDev on 06.05.2018 - 20:14
 */
public class GroundActor extends Box2dActor implements MobEventListener
{
    private final float[] spritePosX;
    private final float spritePosY;
    private final Fixture platform;
    private final Vector2 bodySize;
    private final Array<Sprite> platformSprites;

    private final Array<Sprite> enableGroundSprites;
    private final Sprite undoBorderSprite;

    public static GroundActor buildGroundActor(World world, Vector2 bodyPosition, Vector2 bodySize, Array<Sprite> enableGroundSprites, Sprite undoBorderSprite, float spriteSize)
    {
        Fixture fixture = Box2dObjectBuilder.createBoxFixture(
                world, ConstantBox2dBodyType.GROUND,
                bodyPosition, bodySize.x, bodySize.y);

        return new GroundActor(world, fixture, enableGroundSprites, undoBorderSprite, bodySize, spriteSize);
    }

    public GroundActor(World world, Fixture platform, Array<Sprite> enableGroundSprites, Sprite undoBorderSprite, Vector2 bodySize, float spriteSize)
    {
        super(world);

        this.platform = platform;
        this.bodySize = bodySize;

        int spriteCount = MathUtils.ceil(bodySize.x / spriteSize);
        this.spritePosX = new float[spriteCount];
        this.spritePosY = 0.f;

        float startPosXForSprite = platform.getBody().getPosition().x - bodySize.x / 2.f;

        for (int i = 0; i < spritePosX.length; ++i)
        {
            spritePosX[i] = startPosXForSprite;
            startPosXForSprite += spriteSize;
        }

        this.platformSprites = new Array<Sprite>(spriteCount);

        this.enableGroundSprites = enableGroundSprites;
        this.undoBorderSprite = undoBorderSprite;

        generateGroundSprites();
    }

    public void setContactable(Contactable contactable)
    {
        this.platform.setUserData(contactable);
        this.platform.getBody().setUserData(contactable);
    }

    public boolean isVisibleForPlayer()
    {
        Vector2 groundPlatformPos = platform.getBody().getPosition();
        float cameraPosX = getScene().getCamera().position.x;
        return GameManager.getInstance().isRightDirection()
                ? groundPlatformPos.x + bodySize.x > cameraPosX
                : groundPlatformPos.x - bodySize.x < cameraPosX;
    }

    public void movePlatform(float moveX)
    {
        Vector2 groundPlatformPos = platform.getBody().getPosition();
        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;
        platform.getBody().setTransform(groundPlatformPos.set(groundPlatformPos.x + moveX * direction, groundPlatformPos.y), 0.f);

        updateGroundSprites(moveX);
    }

    private void updateGroundSprites(float moveX)
    {
        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;
        for (int i = 0; i < spritePosX.length; ++i)
        {
            spritePosX[i] += moveX * direction;
        }
        generateGroundSprites();
    }

    private void generateGroundSprites()
    {
        for (int i = 0; i < spritePosX.length; ++i)
        {
            platformSprites.add(enableGroundSprites.get(MathUtils.random(0, enableGroundSprites.size - 1)));
        }
    }

    @Override
    protected void doDraw(Batch batch, float parentAlpha)
    {
        if (!isVisibleForPlayer())
            return;

        for (int i = 0; i < spritePosX.length; ++i)
        {
            drawSprite(batch, platformSprites.get(i), spritePosX[i], spritePosY);
            drawSprite(batch, undoBorderSprite, spritePosX[i], platformSprites.get(i).getHeight());
        }
    }

    @Override
    public void mobKillHero()
    {
        platform.setFriction(100.f);
    }

    @Override
    public void mobChange() { }

}
