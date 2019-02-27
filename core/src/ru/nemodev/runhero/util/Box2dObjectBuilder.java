package ru.nemodev.runhero.util;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import ru.nemodev.runhero.entity.game.Box2dBodyType;
import ru.nemodev.runhero.manager.resource.PhysicManager;

public final class Box2dObjectBuilder
{
    public static Fixture createFixture(Body body, Shape shape, float density, float friction, float restitution)
    {
        FixtureDef fixtureDef = getFixtureDef(shape, density, friction, restitution);
        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

        return fixture;
    }

    public static Fixture createBoxFixture(World world, Box2dBodyType box2dBodyType, Vector2 position, float width, float height)
    {
        Body body = createBody(world, box2dBodyType.getBodyType(), position);
        PolygonShape polygonShape = createBoxShape(width, height);

        return createFixture(body, polygonShape,
                box2dBodyType.getDensity(), box2dBodyType.getFriction(), box2dBodyType.getRestitution());
    }

    public static Fixture createCircleFixture(World world, Box2dBodyType box2dBodyType, Vector2 position, float radius)
    {
        Body body = createBody(world, box2dBodyType.getBodyType(), position);
        CircleShape circleShape = getCircleShape(radius);

        return createFixture(body, circleShape,
                box2dBodyType.getDensity(), box2dBodyType.getFriction(), box2dBodyType.getRestitution());
    }

    public static Fixture createSensorForHeroGround(Body forBody, float radius)
    {
        Vector2[] vertices = {
                new Vector2(), new Vector2(), new Vector2(), new Vector2(),
                new Vector2(), new Vector2(), new Vector2(), new Vector2()
        };
        vertices[0].set(0.f, 0.f);

        for (int i = 0; i < 7; ++i)
        {
            float angle = (float) (i / 6.0 * -90.f * MathUtils.degRad) - 45 * MathUtils.degRad;
            vertices[i+1].set(radius * MathUtils.cos(angle), radius * MathUtils.sin(angle));
        }
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.set(vertices);

        Fixture fixture = createFixture(forBody, polygonShape, 0.f, 0.f, 0.f);
        fixture.setSensor(true);

        return fixture;
    }

    public static BodyDef getBodyDef(BodyDef.BodyType bodyType, Vector2 position)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);

        return bodyDef;
    }

    public static Body createBody(World world, BodyDef.BodyType bodyType, Vector2 position)
    {
        BodyDef bodyDef = getBodyDef(bodyType, position);
        Body body = world.createBody(bodyDef);
        body.resetMassData();
        body.setSleepingAllowed(false);

        return body;
    }

    public static PolygonShape createBoxShape(float width, float height)
    {
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width / 2.f, height / 2.f);

        return polygonShape;
    }

    public static CircleShape getCircleShape(float radius)
    {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius / 2.f);

        return circleShape;
    }

    public static FixtureDef getFixtureDef(Shape shape, float density, float friction, float restitution)
    {
        FixtureDef fixtureDef = getFixtureDef(density, friction, restitution);
        fixtureDef.shape = shape;

        return fixtureDef;
    }

    public static FixtureDef getFixtureDef(float density, float friction, float restitution)
    {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;

        return fixtureDef;
    }

    public static Fixture createFixture(Body body, FixtureDef fixtureDef, float scale, String loaderName, String bodyName)
    {
        PhysicManager.getInstance().loadPhysicBody(loaderName, bodyName, body, fixtureDef, scale);

        return body.getFixtureList().get(0);
    }

}
