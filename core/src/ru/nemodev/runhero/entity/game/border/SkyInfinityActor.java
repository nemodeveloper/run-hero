package ru.nemodev.runhero.entity.game.border;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.core.model.Box2dActor;
import ru.nemodev.runhero.core.physic.collision.Contactable;
import ru.nemodev.runhero.manager.GameManager;


public class SkyInfinityActor extends Box2dActor
{
    private final Array<SkyActor> platforms;
    private final float moveX;

    public SkyInfinityActor(World world, Vector2 platformStartPos, Vector2 platformSize, Array<Sprite> borderSprites, int platformCount)
    {
        super(world);
        this.platforms = new Array<SkyActor>(platformCount);
        this.moveX = (platformCount - 1) * platformSize.x;

        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;
        for (int i = 0; i < platformCount; ++i)
        {
            addSkyActor(SkyActor.buildSkyActor(world, platformStartPos, platformSize, borderSprites, 1.f));
            platformStartPos.add(platformSize.x * direction, 0.f);
        }
    }

    public void setContactable(Contactable contactable)
    {
        for (SkyActor skyActor: new Array.ArrayIterator<SkyActor>(platforms))
        {
            skyActor.setContactable(contactable);
        }
    }

    private void addSkyActor(SkyActor skyActor)
    {
        platforms.add(skyActor);
        addGameObject(skyActor);
    }

    @Override
    protected boolean isNeedUpdate()
    {
        return GameManager.getInstance().isRunning();
    }

    @Override
    protected void doAct(float delta)
    {
        for (SkyActor skyActor: new Array.ArrayIterator<SkyActor>(platforms))
        {
            if (!skyActor.isVisibleForPlayer())
            {
                skyActor.movePlatform(moveX);
                return;
            }
        }
    }

}
