package ru.nemodev.runhero.entity.game.mob;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

public class FlyMobActor extends StaticMobActor
{
    public FlyMobActor(World world, Fixture fixture, Box2DSprite drawable)
    {
        super(world, fixture, drawable);
        body.setGravityScale(0.f);
    }
}
