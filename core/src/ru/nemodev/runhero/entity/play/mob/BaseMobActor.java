package ru.nemodev.runhero.entity.play.mob;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import ru.nemodev.runhero.entity.collision.Contactable;
import ru.nemodev.runhero.entity.common.Box2dActor;
import ru.nemodev.runhero.entity.play.ContactType;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.GameStatus;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;


public abstract class BaseMobActor<T extends Box2DSprite> extends Box2dActor implements Contactable
{
    protected final Fixture fixture;
    protected final Body body;
    protected final T drawable;
    private boolean isNeedUpdate;

    public BaseMobActor(World world, Fixture fixture, T drawable)
    {
        super(world);
        this.fixture = fixture;
        this.body = fixture.getBody();
        this.drawable = drawable;
        this.fixture.getBody().setUserData(this);
        this.isNeedUpdate = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        drawSprite(batch, drawable, body);
    }

    @Override
    protected void doAct(float delta)
    {
        Vector3 cameraPos = getStage().getCamera().position;

        boolean bodyVisible = GameManager.getInstance().isRightDirection()
                ? body.getPosition().x < cameraPos.x - METERS_X
                : body.getPosition().x > cameraPos.x + METERS_X;
        // если тело не видим убираем с экрана
        if (bodyVisible || body.getPosition().y < 0.f)
        {
            world.destroyBody(body);
            remove();
        }

        if (GameManager.getInstance().isGameOver())
        {
            isNeedUpdate = false;
            fixture.setRestitution(0.f);
            body.setLinearVelocity(0.f, 0.f);
        }
    }

    @Override
    protected boolean isNeedUpdate()
    {
        return isNeedUpdate;
    }

    @Override
    public Actor hit(float x, float y, boolean touchable)
    {
        return null;
    }

    @Override
    public void beginContact(Contactable contactable)
    {
        if (GameManager.getInstance().isRunning()
                && contactable.getContactType() == ContactType.PLAYER)
        {
            GameManager.getInstance().setGameStatus(GameStatus.GAME_OVER);

            fixture.setRestitution(0.f);
            body.setLinearVelocity(0.f, 0.f);
            for (MobEventListener mobEventListener : GameManager.getInstance().getMobEventListeners())
            {
                mobEventListener.mobKillHero();
            }
        }
    }

    @Override
    public void endContact(Contactable contactable)
    {

    }

    @Override
    public ContactType getContactType()
    {
        return ContactType.MOB;
    }

}
