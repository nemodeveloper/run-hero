package ru.nemodev.runhero.entity.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import ru.nemodev.runhero.entity.collision.Contactable;
import ru.nemodev.runhero.entity.play.ContactType;


public abstract class Box2dActor extends BaseActor implements Contactable
{
    protected final World world;

    public Box2dActor(World world)
    {
        this.world = world;
    }

    protected void drawSprite(Batch batch, Box2DSprite sprite, Body body)
    {
        sprite.draw(batch, body);
    }

    protected void drawSprite(Batch batch, Box2DSprite sprite, Fixture fixture)
    {
        sprite.draw(batch, fixture);
    }

    protected void drawSprite(Batch batch, Sprite sprite, Body body)
    {
        final Vector2 position = body.getPosition();
        sprite.setRotation(MathUtils.radiansToDegrees * body.getAngle());
        sprite.setPosition(position.x - sprite.getWidth() / 2.f, position.y - sprite.getHeight() / 2.f);
        sprite.draw(batch);
    }

    @Override
    public void beginContact(Contactable contactable)
    { }

    @Override
    public void endContact(Contactable contactable)
    { }

    @Override
    public ContactType getContactType()
    {
        return ContactType.UNKNOWN;
    }
}
