package ru.nemodev.runhero.screen.main;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import ru.nemodev.runhero.core.manager.GameStatus;
import ru.nemodev.runhero.core.scene.Scene;
import ru.nemodev.runhero.core.screen.BaseScreen;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.scene.main.MainBackgroundScene;
import ru.nemodev.runhero.scene.main.MainMenuScene;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;
import static ru.nemodev.runhero.constant.GameConstant.METERS_Y;

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
        OrthographicCamera camera = new OrthographicCamera(METERS_X, METERS_Y);
        camera.setToOrtho(false, METERS_X, METERS_Y);

        MainBackgroundScene mainBackgroundScene = new MainBackgroundScene(
                new ExtendViewport(METERS_X, METERS_Y, METERS_X, METERS_Y, camera), batch);

        addScene(mainBackgroundScene);
    }

    private void initMenuScene(Batch batch)
    {
        OrthographicCamera camera = new OrthographicCamera(METERS_X, METERS_Y);
        camera.setToOrtho(false, METERS_X, METERS_Y);

        MainMenuScene mainMenuScene = new MainMenuScene(
                new ExtendViewport(METERS_X, METERS_Y, METERS_X, METERS_Y, camera), batch);

        addScene(mainMenuScene);
    }

    @Override
    public void dispose()
    {
        super.dispose();
    }
}
