package ru.nemodev.runhero.entity.game.score_item;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import ru.nemodev.runhero.core.model.Box2dActor;
import ru.nemodev.runhero.core.physic.collision.Contactable;
import ru.nemodev.runhero.entity.game.ContactType;
import ru.nemodev.runhero.manager.GameManager;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;


public class ScoreItemActor extends Box2dActor
{
    private final Box2DSprite scoreSprite;
    protected final Fixture scoreFixture;
    protected final Body scoreBody;
    protected boolean needRemove;

    public ScoreItemActor(World world, Box2DSprite scoreSprite, Fixture scoreFixture)
    {
        super(world);
        this.scoreSprite = scoreSprite;
        this.scoreFixture = scoreFixture;
        this.scoreFixture.setUserData(this);
        this.scoreBody = scoreFixture.getBody();
        this.needRemove = false;
    }

    @Override
    protected void doDraw(Batch batch, float parentAlpha)
    {
        drawSprite(batch, scoreSprite, scoreBody);
    }

    @Override
    protected void doAct(float delta)
    {
        Vector3 cameraPos = getScene().getCamera().position;

        boolean bodyVisible = GameManager.getInstance().isRightDirection()
                ? scoreBody.getPosition().x > cameraPos.x - METERS_X
                : scoreBody.getPosition().x < cameraPos.x + METERS_X;

        if (!bodyVisible || needRemove)
        {
            remove();
        }
        else
        {
            scoreSprite.rotate(1.f);
        }
    }

    @Override
    public void remove()
    {
        super.remove();
        world.destroyBody(scoreBody);
    }

    @Override
    public void beginContact(Contactable contactable)
    {
        if (contactable.getContactType() == ContactType.PLAYER && GameManager.getInstance().isRunning())
        {
            needRemove = true;
            scoreFixture.setUserData(null);
        }
    }

    @Override
    public void endContact(Contactable contactable)
    {

    }

    @Override
    public ContactType getContactType()
    {
        return ContactType.SCORE_ITEM;
    }
}
