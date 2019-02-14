package ru.nemodev.runhero.entity.game;

import com.badlogic.gdx.physics.box2d.BodyDef;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;
import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;

public enum ConstantBox2dBodyType implements Box2dBodyType
{
    PLAYER(DynamicBody, 3.f, 1.f, 0.f),
    GROUND(StaticBody, 0.f, 0.0f, 0.0f),
    SCORE_ITEM(StaticBody, 0.f, 0.0f, 0.0f),
    STATIC_MOB(DynamicBody, 1.f, 1.0f, 0.f);

    private final BodyDef.BodyType bodyType;
    private final float density;      // масса // количество килограмм на 1 квадратный метр площади объекта
    private final float friction;     // сила трения
    private final float restitution;  // эластичность

    ConstantBox2dBodyType(BodyDef.BodyType bodyType, float density, float friction, float restitution)
    {
        this.bodyType = bodyType;
        this.density = density;
        this.friction = friction;
        this.restitution = restitution;
    }

    public BodyDef.BodyType getBodyType()
    {
        return bodyType;
    }

    public float getDensity()
    {
        return density;
    }

    public float getFriction()
    {
        return friction;
    }

    public float getRestitution()
    {
        return restitution;
    }
}
