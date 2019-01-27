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

import ru.nemodev.runhero.entity.play.Box2dBodyType;

public final class Box2dObjectBuilder
{
    public static Fixture createPolygonFixture(World world, Box2dBodyType box2dBodyType, Vector2 position, float width, float height)
    {
        Body body = createBody(world, box2dBodyType.getBodyType(), position);
        PolygonShape polygonShape = createPolygon(width, height);

        return createFixture(body, polygonShape,
                box2dBodyType.getDensity(), box2dBodyType.getFriction(), box2dBodyType.getRestitution());
    }

    public static Fixture createCircleFixture(World world, Box2dBodyType box2dBodyType, Vector2 position, float radius)
    {
        Body body = createBody(world, box2dBodyType.getBodyType(), position);
        CircleShape circleShape = createCircle(radius);

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

    public static Fixture createFixture(Body body, Shape shape, float density, float friction, float restitution)
    {
        FixtureDef fixtureDef = createFixtureDef(shape, density, friction, restitution);
        shape.dispose();

        return body.createFixture(fixtureDef);
    }

    public static Body createBody(World world, BodyDef.BodyType bodyType, Vector2 position)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position.x, position.y);

        return world.createBody(bodyDef);
    }

    public static PolygonShape createPolygon(float width, float height)
    {
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width / 2.f, height / 2.f);

        return polygonShape;
    }

    public static CircleShape createCircle(float radius)
    {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius / 2.f);

        return circleShape;
    }

    public static FixtureDef createFixtureDef(Shape shape, float density, float friction, float restitution)
    {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;

        return fixtureDef;
    }

}
