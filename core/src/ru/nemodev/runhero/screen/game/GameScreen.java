package ru.nemodev.runhero.screen.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import ru.nemodev.runhero.core.manager.GameStatus;
import ru.nemodev.runhero.core.scene.Scene;
import ru.nemodev.runhero.core.screen.BaseScreen;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.scene.game.GameBackgroundScene;
import ru.nemodev.runhero.scene.game.GameScene;
import ru.nemodev.runhero.scene.game.GameUIScene;
import ru.nemodev.runhero.scene.game.ScoreScene;

/**
 * created by NemoDev on 06.05.2018 - 19:31
 */
public class GameScreen extends BaseScreen
{
    private GameBackgroundScene gameBackgroundScene;
    private GameScene gameScene;
    private ScoreScene scoreScene;
    private GameUIScene gameUIScene;

    public GameScreen()
    {
        super(new Array<Scene>());

        Batch batch = GameManager.getInstance().getSpriteBatch();

        initBackgroundScene(batch);
        initGameScene(batch);
        initScoreScene(batch);
        initGameUIScene(batch);
    }

    private void initBackgroundScene(Batch batch)
    {
        gameBackgroundScene = new GameBackgroundScene(batch);
        addScene(gameBackgroundScene);
    }

    private void initGameScene(Batch batch)
    {
        gameScene = new GameScene(new World(new Vector2(0.f, -9.81f), false), batch);

        addScene(gameScene);
    }

    private void initScoreScene(Batch batch)
    {
        scoreScene = new ScoreScene(new ScreenViewport(), batch);
        addScene(scoreScene);
    }

    private void initGameUIScene(Batch batch)
    {
        gameUIScene = new GameUIScene(batch, gameScene.getSoundEventListener());
        addScene(gameUIScene);
    }

    @Override
    protected GameStatus getGameStatus()
    {
        return GameStatus.RUNNING;
    }

}
