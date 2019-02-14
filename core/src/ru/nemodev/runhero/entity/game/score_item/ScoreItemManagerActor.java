package ru.nemodev.runhero.entity.game.score_item;

import com.badlogic.gdx.physics.box2d.World;

import ru.nemodev.runhero.entity.common.Box2dActor;
import ru.nemodev.runhero.entity.game.ScoreChangeListener;
import ru.nemodev.runhero.manager.GameManager;

public class ScoreItemManagerActor extends Box2dActor implements ScoreChangeListener
{
    private final ScoreItemFactory scoreItemFactory;

    public ScoreItemManagerActor(World world)
    {
        super(world);
        this.scoreItemFactory = new ScoreItemFactory(world);
    }

    @Override
    protected boolean isNeedUpdate()
    {
        return GameManager.getInstance().isRunning();
    }

    @Override
    protected void doAct(float delta)
    {
        ScoreItemActor scoreItemActor = scoreItemFactory.spawn(delta, getStage().getCamera().position);
        if (scoreItemActor != null)
        {
            addActor(scoreItemActor);
        }
    }

    @Override
    public void scoreChange(int score)
    {

    }
}
