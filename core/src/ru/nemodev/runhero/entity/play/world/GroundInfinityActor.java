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


public class GroundInfinityActor extends Box2dActor implements MobEventListener
{
    private final Array<GroundActor> platforms;
    private final float moveX;

    public GroundInfinityActor(World world, Vector2 platformStartPos, Vector2 platformSize, Array<Sprite> enableGroundSprites, Sprite undoGroundSprite, int platformCount)
    {
        super(world);
        this.platforms = new Array<GroundActor>(platformCount);
        this.moveX = (platformCount - 1) * platformSize.x;

        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;
        for (int i = 0; i < platformCount; ++i)
        {
            addGroundActor(GroundActor.buildGroundActor(world, platformStartPos, platformSize, enableGroundSprites, undoGroundSprite, 1.f));
            platformStartPos.add(platformSize.x * direction, 0.f);
        }
    }

    public void setContactable(Contactable contactable)
    {
        for (GroundActor groundActor : platforms)
        {
            groundActor.setContactable(contactable);
        }
    }

    private void addGroundActor(GroundActor groundActor)
    {
        platforms.add(groundActor);
        addActor(groundActor);
    }

    @Override
    protected void doAct(float delta)
    {
        for (GroundActor groundActor : platforms)
        {
            if (!groundActor.isVisibleForPlayer())
            {
                groundActor.movePlatform(moveX);
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
        for (GroundActor groundActor : platforms)
        {
            groundActor.mobKillHero();
        }
    }

    @Override
    public void mobChange() { }

}
