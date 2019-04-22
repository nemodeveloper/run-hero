package ru.nemodev.runhero.core.screen;

import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.core.manager.GameStatus;
import ru.nemodev.runhero.core.manager.resource.ResourceLoader;
import ru.nemodev.runhero.core.scene.Scene;

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
        return ResourceLoader.getInstance().isFinishLoad();
    }

    @Override
    protected GameStatus getGameStatus()
    {
        return GameStatus.LOADING;
    }
}
