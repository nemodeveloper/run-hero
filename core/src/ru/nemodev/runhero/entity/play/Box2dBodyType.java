package ru.nemodev.runhero.entity.play;

import com.badlogic.gdx.physics.box2d.BodyDef;

public interface Box2dBodyType
{
    BodyDef.BodyType getBodyType();

    float getDensity();

    float getFriction();

    float getRestitution();
}
