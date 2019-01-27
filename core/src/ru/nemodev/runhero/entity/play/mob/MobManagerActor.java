package ru.nemodev.runhero.entity.play.mob;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ru.nemodev.runhero.entity.common.Box2dActor;
import ru.nemodev.runhero.entity.play.ContactType;
import ru.nemodev.runhero.entity.play.ScoreChangeListener;
import ru.nemodev.runhero.manager.GameManager;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;
import static ru.nemodev.runhero.constant.GameConstant.WORLD_UNIT;


public class MobManagerActor extends Box2dActor implements ScoreChangeListener
{
    private final MobFactory mobFactory;

    public MobManagerActor(World world)
    {
        super(world);
        this.mobFactory = new MobFactory(this.world, new Vector2(
                GameManager.getInstance().isRightDirection() ? METERS_X : -METERS_X, WORLD_UNIT * 2));
    }

    @Override
    public void doAct(float delta)
    {
        BaseMobActor MobActor = mobFactory.getMob(getStage().getCamera().position);
        if (MobActor != null)
        {
            addActor(MobActor);
        }

        super.doAct(delta);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable)
    {
        return null;
    }

    @Override
    public void scoreChange(int score)
    {
        mobFactory.setPlayerScore(score);
    }

    @Override
    public ContactType getContactType()
    {
        return ContactType.MOB;
    }
}
