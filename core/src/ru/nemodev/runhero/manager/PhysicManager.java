package ru.nemodev.runhero.manager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

import ru.nemodev.runhero.constant.physic.MobPhysicConstant;
import ru.nemodev.runhero.constant.physic.PhysicLoaderConstant;
import ru.nemodev.runhero.physic.BodyEditorLoader;
import ru.nemodev.runhero.util.FileUtils;

/**
 * created by NemoDev on 08.05.2018 - 20:45
 */
public final class PhysicManager implements Disposable
{
    private static volatile PhysicManager instance;

    private Map<String, BodyEditorLoader> bodyEditorLoaderMap;

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
    {
        bodyEditorLoaderMap = new HashMap<String, BodyEditorLoader>();
        bodyEditorLoaderMap.put(PhysicLoaderConstant.STONE_MOBS, new BodyEditorLoader(FileUtils.getInternalFile(MobPhysicConstant.MOB_STONE_PATH)));
    }

    public void loadPhysicBody(String loaderName, String bodyName, Body body, FixtureDef fixtureDef, float scale)
    {
        bodyEditorLoaderMap.get(loaderName).attachFixture(body, bodyName, fixtureDef, scale);
    }

    @Override
    public void dispose()
    { }
}
