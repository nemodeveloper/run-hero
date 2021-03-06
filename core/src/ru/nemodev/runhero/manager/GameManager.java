package ru.nemodev.runhero.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.core.manager.GameStatus;
import ru.nemodev.runhero.core.manager.ScreenManager;
import ru.nemodev.runhero.core.manager.system.AppServiceManager;
import ru.nemodev.runhero.core.service.AdsService;
import ru.nemodev.runhero.core.util.InputUtils;
import ru.nemodev.runhero.entity.game.ScoreChangeListener;
import ru.nemodev.runhero.entity.game.mob.kind.MobEventListener;
import ru.nemodev.runhero.screen.game.GameScreen;

/**
 * created by NemoDev on 08.05.2018 - 20:45
 */
public final class GameManager
{
    private static volatile GameManager instance = new GameManager();

    private GameStatus gameStatus;

    private ScreenManager screenManager;
    private final Batch spriteBatch;

    private Array<ScoreChangeListener> scoreChangeListeners;
    private Array<MobEventListener> mobEventListeners;
    private boolean rightDirection;
    private int newGameCount = 0;

    private GameManager()
    {
        spriteBatch = new SpriteBatch();
        scoreChangeListeners = new Array<ScoreChangeListener>();
        mobEventListeners = new Array<MobEventListener>();
        rightDirection = true;
    }

    public static GameManager getInstance()
    {
        return instance;
    }

    public Batch getSpriteBatch()
    {
        return spriteBatch;
    }

    public void addScoreChangeListener(ScoreChangeListener scoreChangeListener)
    {
        this.scoreChangeListeners.add(scoreChangeListener);
    }

    public Array<ScoreChangeListener> getScoreChangeListeners()
    {
        return scoreChangeListeners;
    }

    public Array<MobEventListener> getMobEventListeners()
    {
        return mobEventListeners;
    }

    public void addMobEventListener(MobEventListener mobEventListener)
    {
        this.mobEventListeners.add(mobEventListener);
    }

    public boolean isGameOver()
    {
        return gameStatus == GameStatus.GAME_OVER;
    }

    public boolean isRunning()
    {
        return gameStatus == GameStatus.RUNNING;
    }

    public boolean isPause()
    {
        return gameStatus == GameStatus.PAUSE;
    }

    public GameStatus getGameStatus()
    {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus)
    {
        this.gameStatus = gameStatus;

        if (isGameOver())
        {
            InputUtils.setInputProcessor(new InputAdapter()
            {
                boolean isStartNewGame = false;

                @Override
                public boolean touchDown(int screenX, int screenY, int pointer, int button)
                {
                    return true;
                }

                @Override
                public boolean touchUp(int screenX, int screenY, int pointer, int button)
                {
                    if (!isStartNewGame)
                    {
                        isStartNewGame = true;
                        ++newGameCount;

                        if (newGameCount == 2)
                        {
                            newGameCount = 0;
                            AppServiceManager.getInstance().getAdsService().showFullScreenBanner(new AdsService.AdsListener()
                            {
                                @Override
                                public void adsShowed(boolean showed)
                                {

                                }
                            });
                        }

                        reloadForNewGame();
                        getScreenManager().popScreen();
                        getScreenManager().pushScreen(new GameScreen());
                    }
                    return true;
                }
            });
        }
    }

    public void setScreenManager(ScreenManager screenManager)
    {
        this.screenManager = screenManager;
    }

    public ScreenManager getScreenManager()
    {
        return screenManager;
    }

    public boolean isRightDirection()
    {
        return rightDirection;
    }

    public void exit()
    {
        Gdx.app.exit();
        System.exit(0);
    }

    public void reloadForNewGame()
    {
        mobEventListeners.clear();
        scoreChangeListeners.clear();
        rightDirection = !rightDirection;
        gameStatus = GameStatus.RUNNING;
    }

    public void flushGame()
    {
        mobEventListeners.clear();
        scoreChangeListeners.clear();
        rightDirection = true;
        gameStatus = GameStatus.UNKNOWN;
    }

}
