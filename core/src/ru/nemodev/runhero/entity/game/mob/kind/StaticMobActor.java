package ru.nemodev.runhero.entity.game.mob.kind;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

public class StaticMobActor extends BaseMobActor<Box2DSprite>
{
    public StaticMobActor(World world, Fixture fixture, Box2DSprite sprite)
    {
        super(world, fixture, sprite);
    }

}
