package ru.nemodev.runhero.entity.game.mob.kind;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import ru.nemodev.runhero.core.manager.GameStatus;
import ru.nemodev.runhero.core.model.Box2dActor;
import ru.nemodev.runhero.core.physic.collision.Contactable;
import ru.nemodev.runhero.entity.game.ContactType;
import ru.nemodev.runhero.manager.GameManager;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;


public abstract class BaseMobActor<T extends Box2DSprite> extends Box2dActor implements Contactable
{
//    private static ShaderProgram shaderOutline = buildOutLineShader();
//    private static ShaderProgram buildOutLineShader()
//    {
//        String vertexShader = Gdx.files.internal("shader/df_vertex.glsl").readString();
//        String fragmentShader = Gdx.files.internal("shader/outline_border.glsl").readString();
//
//        ShaderProgram shaderOutline = new ShaderProgram(vertexShader, fragmentShader);
//
//        if (!shaderOutline.isCompiled())
//            throw new GdxRuntimeException("Couldn't compile shader: " + shaderOutline.getLog());
//
//        return shaderOutline;
//    }

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
    protected void doDraw(Batch batch, float parentAlpha)
    {
//        shaderOutline.begin();
//        shaderOutline.setUniformf("u_viewportInverse", new Vector2(1f / drawable.getWidth(), 1f / drawable.getHeight()));
//        shaderOutline.setUniformf("u_offset", 3.f);
//        shaderOutline.setUniformf("u_step", Math.min(1f, drawable.getWidth() / 70f));
//        shaderOutline.setUniformf("u_color", new Vector3(Color.YELLOW.r, Color.YELLOW.g, Color.YELLOW.b));
//        shaderOutline.end();
//
//        batch.setShader(shaderOutline);
//        drawSprite(batch, drawable, body);
//        batch.setShader(null);
//        drawSprite(batch, drawable, body);
//        batch.end();
//        batch.begin();

        drawSprite(batch, drawable, body);
    }

    @Override
    protected void doAct(float delta)
    {
        Vector3 cameraPos = getScene().getCamera().position;

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
