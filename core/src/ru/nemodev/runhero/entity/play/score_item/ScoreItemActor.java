package ru.nemodev.runhero.entity.play.score_item;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import ru.nemodev.runhero.entity.collision.Contactable;
import ru.nemodev.runhero.entity.common.Box2dActor;
import ru.nemodev.runhero.entity.play.ContactType;
import ru.nemodev.runhero.manager.GameManager;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;


public class ScoreItemActor<T extends Box2DSprite> extends Box2dActor
{
    private final T itemSprite;
    private final Fixture scoreItemFixture;
    private final Body itemBody;
    private boolean needRemove;

    public ScoreItemActor(World world, T itemSprite, Fixture scoreItemFixture)
    {
        super(world);
        this.itemSprite = itemSprite;
        this.scoreItemFixture = scoreItemFixture;
        this.scoreItemFixture.setUserData(this);
        this.itemBody = scoreItemFixture.getBody();
        this.needRemove = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        drawSprite(batch, itemSprite, itemBody);
    }

    @Override
    protected void doAct(float delta)
    {
        Vector3 cameraPos = getStage().getCamera().position;

        boolean bodyVisible = GameManager.getInstance().isRightDirection()
                ? itemBody.getPosition().x > cameraPos.x - METERS_X
                : itemBody.getPosition().x < cameraPos.x + METERS_X;

        if (!bodyVisible || needRemove)
        {
            remove();
        }
    }

    @Override
    public boolean remove()
    {
        world.destroyBody(itemBody);
        return super.remove();
    }

    @Override
    public void beginContact(Contactable contactable)
    {
        if (contactable.getContactType() == ContactType.PLAYER)
        {
            needRemove = true;
            scoreItemFixture.setUserData(null);
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
