package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.entity.play.ScoreViewActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.PoolManager;
import ru.nemodev.runhero.scene.common.BaseScene;

public class ScoreScene extends BaseScene
{
    private ScoreViewActor scoreViewActor;

    public ScoreScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);
    }

    @Override
    public void init()
    {
        super.init();

        scoreViewActor = PoolManager.getInstance().get(ScoreViewActor.class);
        addActor(scoreViewActor);

        GameManager.getInstance().addScoreChangeListener(scoreViewActor);
        GameManager.getInstance().addMobEventListener(scoreViewActor);

    }

    @Override
    public void dispose()
    {
        super.dispose();

        PoolManager.getInstance().free(scoreViewActor);
    }
}
