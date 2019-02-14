package ru.nemodev.runhero.entity.game.mob;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.AnimatedBox2DSprite;

public class AnimationMobActor extends BaseMobActor<AnimatedBox2DSprite>
{
    public AnimationMobActor(World world, Fixture fixture, AnimatedBox2DSprite mobAnimation)
    {
        super(world, fixture, mobAnimation);
    }

    @Override
    protected void doAct(float delta)
    {
        super.doAct(delta);
        drawable.update(delta);
    }
}
