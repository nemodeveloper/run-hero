package ru.nemodev.runhero.screen.common;

import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.manager.ResourceManager;
import ru.nemodev.runhero.scene.common.Scene;

public abstract class BaseLoaderScreen extends BaseScreen
{
    private boolean isFinishLoadRes;

    public BaseLoaderScreen()
    {
        this(new Array<Scene>());
    }

    public BaseLoaderScreen(Array<Scene> scenes)
    {
        super(scenes);
        loadResources();
    }

    @Override
    public void render(float delta)
    {
        if (!isFinishLoadRes && isFinishLoadResources())
        {
            isFinishLoadRes = true;
            doAfterLoadResource();
            return;
        }

        super.render(delta);
    }

    protected void loadResources() {}
    protected void doAfterLoadResource() {}

    protected boolean isFinishLoadResources()
    {
        return ResourceManager.getInstance().isFinishLoad();
    }

}
