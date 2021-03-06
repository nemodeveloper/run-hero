package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.core.manager.GameStatus;
import ru.nemodev.runhero.core.scene.BaseScene;
import ru.nemodev.runhero.entity.game.ScoreViewActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.pool.PoolManager;

public class ScoreScene extends BaseScene
{
    private ScoreViewActor scoreViewActor;

    public ScoreScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);

        init();
    }

    private void init()
    {
        scoreViewActor = PoolManager.getInstance().get(ScoreViewActor.class);
        addGameObject(scoreViewActor);

        GameManager.getInstance().addScoreChangeListener(scoreViewActor);
        GameManager.getInstance().addMobEventListener(scoreViewActor);

    }

    @Override
    public void dispose()
    {
        super.dispose();

        PoolManager.getInstance().free(scoreViewActor);
    }

    @Override
    protected boolean isNeedUpdate()
    {
        return GameManager.getInstance().getGameStatus() == GameStatus.RUNNING;
    }
}
