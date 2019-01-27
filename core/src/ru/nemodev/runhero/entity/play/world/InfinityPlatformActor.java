package ru.nemodev.runhero.entity.play.world;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.entity.collision.Contactable;
import ru.nemodev.runhero.entity.common.Box2dActor;
import ru.nemodev.runhero.entity.play.ContactType;
import ru.nemodev.runhero.entity.play.mob.MobEventListener;
import ru.nemodev.runhero.manager.GameManager;

import static ru.nemodev.runhero.constant.GameConstant.WORLD_UNIT;


public class InfinityPlatformActor extends Box2dActor implements MobEventListener
{
    private final Array<PlatformActor> platforms;
    private final float moveX;

    public InfinityPlatformActor(World world, Vector2 platformStartPos, Vector2 platformSize, Array<Sprite> enableSprite, int platformCount)
    {
        super(world);
        this.platforms = new Array<PlatformActor>(platformCount);
        this.moveX = (platformCount - 1) * platformSize.x;

        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;
        for (int i = 0; i < platformCount; ++i)
        {
            addPlatform(PlatformActor.buildPlatformActor(world, platformStartPos, platformSize, enableSprite, WORLD_UNIT * 2));
            platformStartPos.add(platformSize.x * direction, 0.f);
        }
    }

    public void setContactable(Contactable contactable)
    {
        for (PlatformActor platformActor : platforms)
        {
            platformActor.setContactable(contactable);
        }
    }

    private void addPlatform(PlatformActor platformActor)
    {
        platforms.add(platformActor);
        addActor(platformActor);
    }

    @Override
    protected void doAct(float delta)
    {
        for (PlatformActor platformActor : platforms)
        {
            if (!platformActor.isVisibleForPlayer())
            {
                platformActor.movePlatform(moveX);
                return;
            }
        }
    }

    @Override
    public ContactType getContactType()
    {
        return ContactType.GROUND;
    }

    @Override
    public void mobKillHero()
    {
        for (PlatformActor platformActor : platforms)
        {
            platformActor.mobKillHero();
        }
    }

    @Override
    public void mobChange()
    {

    }
}
