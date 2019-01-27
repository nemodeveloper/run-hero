package ru.nemodev.runhero.manager;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

/**
 * created by NemoDev on 08.05.2018 - 20:45
 */
public final class PhysicManager implements Disposable
{
    private static volatile PhysicManager instance;

    private PhysicManager()
    { }

    public static PhysicManager getInstance()
    {
        if (instance == null)
        {
            synchronized (PhysicManager.class)
            {
                if (instance == null)
                {
                    instance = new PhysicManager();
                    instance.initPhysic();
                    instance.loadPhysic();
                }
            }
        }

        return instance;
    }

    private void initPhysic()
    {
        Box2D.init();
    }

    private void loadPhysic()
    { }

    public Body getBody(World world, String bodyName, float scaleX, float scaleY)
    {
        return null;
    }

    @Override
    public void dispose()
    { }
}
