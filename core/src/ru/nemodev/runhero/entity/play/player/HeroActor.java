package ru.nemodev.runhero.entity.play.player;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;

import net.dermetfan.gdx.graphics.g2d.AnimatedBox2DSprite;

import ru.nemodev.runhero.entity.collision.Contactable;
import ru.nemodev.runhero.entity.common.Box2dActor;
import ru.nemodev.runhero.entity.play.ContactType;
import ru.nemodev.runhero.entity.play.ScoreChangeListener;
import ru.nemodev.runhero.entity.play.mob.MobEventListener;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.util.Box2dObjectBuilder;


/**
 * created by NemoDev on 06.05.2018 - 20:14
 */
public class HeroActor extends Box2dActor implements Contactable, MobEventListener
{
    private final Fixture heroFixture;
    private final Array<AnimatedBox2DSprite> heroAnimations;
    private final Vector2 startVelocity;

    private Fixture groundSensorFixture;

    private volatile boolean onGround;

    private volatile boolean isTouchDown;
    private volatile float startPressY;

    private int gameScore;

    public HeroActor(World world, final Fixture heroFixture, Array<AnimatedBox2DSprite> heroAnimations, Vector2 startVelocity, float width, float height)
    {
        super(world);

        this.heroFixture = heroFixture;
        this.heroFixture.setUserData(this);
        this.heroAnimations = heroAnimations;
        this.startVelocity = startVelocity;
        this.onGround = false;

        this.isTouchDown = false;

        this.gameScore = 0;

        initWorld(height / 1.3f);

        addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if (isTouchDown)
                    return true;

                if (onGround)
                {
                    startPressY = y;
                    isTouchDown = true;
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                if (onGround && isTouchDown)
                {
                    if (y > startPressY)
                    {
                        float impulseY = (y - startPressY) * 450.f;
                        heroFixture.getBody().applyForceToCenter(0.f, impulseY, true);
                    }

                    isTouchDown = false;
                }
            }

        });
    }

    private void initWorld(float sensorGroundRadius)
    {
        groundSensorFixture = Box2dObjectBuilder.createSensorForHeroGround(heroFixture.getBody(), sensorGroundRadius);
        groundSensorFixture.setUserData(new Contactable()
        {
            int groundBlock = 0;

            @Override
            public void beginContact(Contactable contactable)
            {
                if (contactable.getContactType() == ContactType.GROUND)
                {
                    setOnGround(true);
                    ++groundBlock;
                }
            }

            @Override
            public void endContact(Contactable contactable)
            {
                if (contactable.getContactType() == ContactType.GROUND)
                {
                    --groundBlock;
                    if (groundBlock == 0)
                    {
                        setOnGround(false);
                    }
                }
            }

            @Override
            public ContactType getContactType()
            {
                return ContactType.OTHER;
            }
        });

    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        drawSprite(batch, getCurrentAnimation(), heroFixture);
    }

    @Override
    protected void doAct(float delta)
    {
        if (GameManager.getInstance().isRunning())
        {
            // хак в момент остановки героя из-за цепляния за платформу
            if (heroFixture.getBody().getLinearVelocity().x < startVelocity.x)
            {
                heroFixture.getBody().setLinearVelocity(startVelocity);
            }
            updateHeroAnimation(delta);
        }
        else
            heroAnimations.get(0).setPlaying(false);

        // TODO тонкий момент по сдвигу камеру - зависит от начального положения героя
        Camera camera = getStage().getCamera();

        float shift = 7 * (GameManager.getInstance().isRightDirection() ? 1.f : -1.f);
        camera.position.x = heroFixture.getBody().getPosition().x + shift;
    }

    private void updateHeroAnimation(float delta)
    {
        for (AnimatedBox2DSprite animation : heroAnimations)
        {
            animation.update(delta);
        }
    }

    private AnimatedBox2DSprite getCurrentAnimation()
    {
        if (isTouchDown)
            return heroAnimations.get(1);

        return heroAnimations.get(0);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable)
    {
        return this;
    }

    public void setOnGround(boolean onGround)
    {
        this.onGround = onGround;
    }

    @Override
    public void beginContact(Contactable contactable)
    {
        if (contactable.getContactType() == ContactType.SCORE_ITEM)
        {
            ++gameScore;
            Array<ScoreChangeListener> scoreChangeListeners = GameManager.getInstance().getScoreChangeListeners();
            if (scoreChangeListeners != null && GameManager.getInstance().isRunning())
            {
                for (ScoreChangeListener scoreChangeListener : scoreChangeListeners)
                {
                    scoreChangeListener.scoreChange(gameScore);
                }
            }
        }
    }

    @Override
    public ContactType getContactType()
    {
        return ContactType.PLAYER;
    }

    @Override
    public void mobKillHero()
    {
        heroFixture.getBody().setLinearVelocity(0.f, 0.f);
    }

    @Override
    public void mobChange()
    {
        float bonusSpeed = GameManager.getInstance().isRightDirection() ? 0.5f : -0.5f;
        startVelocity.add(bonusSpeed, 0.f);
        heroFixture.getBody().setLinearVelocity(heroFixture.getBody().getLinearVelocity().add(bonusSpeed, 0.f));
    }
}
