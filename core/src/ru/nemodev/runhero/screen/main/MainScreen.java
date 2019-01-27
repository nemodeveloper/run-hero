package ru.nemodev.runhero.screen.main;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.GameStatus;
import ru.nemodev.runhero.scene.main.TutorialScene;
import ru.nemodev.runhero.screen.common.BaseScreen;

/**
 * created by NemoDev on 06.05.2018 - 19:30
 */
public class MainScreen extends BaseScreen
{
    public MainScreen()
    {
        super();

        Viewport viewport = new ScreenViewport();
        addScene(new TutorialScene(viewport, GameManager.getInstance().getSpriteBatch()));

        GameManager.getInstance().setGameStatus(GameStatus.READY);
    }
}
