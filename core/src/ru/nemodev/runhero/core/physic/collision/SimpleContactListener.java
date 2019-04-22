package ru.nemodev.runhero.core.physic.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class SimpleContactListener implements ContactListener
{
    @Override
    public void beginContact(Contact contact)
    {
        Contactable a = (Contactable) contact.getFixtureA().getUserData();
        if (a == null)
        {
            a = (Contactable) contact.getFixtureA().getBody().getUserData();
        }

        Contactable b = (Contactable) contact.getFixtureB().getUserData();
        if (b == null)
        {
            b = (Contactable) contact.getFixtureB().getBody().getUserData();
        }

        if (a == null || b == null)
            return;

        a.beginContact(b);
        b.beginContact(a);
    }

    @Override
    public void endContact(Contact contact)
    {
        Contactable a = (Contactable) contact.getFixtureA().getUserData();
        Contactable b = (Contactable) contact.getFixtureB().getUserData();

        if (a == null || b == null)
            return;

        a.endContact(b);
        b.endContact(a);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {

    }
}
