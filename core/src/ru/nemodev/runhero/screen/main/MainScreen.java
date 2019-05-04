package ru.nemodev.runhero.screen.main;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.core.manager.GameStatus;
import ru.nemodev.runhero.core.scene.Scene;
import ru.nemodev.runhero.core.screen.BaseScreen;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.scene.main.MainBackgroundScene;
import ru.nemodev.runhero.scene.main.MainMenuScene;


/**
 * created by NemoDev on 06.05.2018 - 19:30
 */
public class MainScreen extends BaseScreen
{
    public MainScreen()
    {
        super(new Array<Scene>());

        Batch batch = GameManager.getInstance().getSpriteBatch();

        initBackgroundScene(batch);
        initMenuScene(batch);
    }

    @Override
    protected GameStatus getGameStatus()
    {
        return GameStatus.READY;
    }

    private void initBackgroundScene(Batch batch)
    {
        MainBackgroundScene mainBackgroundScene = new MainBackgroundScene(batch);

        addScene(mainBackgroundScene);
    }

    private void initMenuScene(Batch batch)
    {
        MainMenuScene mainMenuScene = new MainMenuScene(batch);

        addScene(mainMenuScene);
    }

    @Override
    public void dispose()
    {
        super.dispose();
    }
}
