package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.BorderTextureConstant;
import ru.nemodev.runhero.entity.collision.Contactable;
import ru.nemodev.runhero.entity.game.ConstantBox2dBodyType;
import ru.nemodev.runhero.entity.game.ContactType;
import ru.nemodev.runhero.entity.game.mob.MobEventListener;
import ru.nemodev.runhero.entity.game.mob.MobManagerActor;
import ru.nemodev.runhero.entity.game.player.HeroActor;
import ru.nemodev.runhero.entity.game.score_item.ScoreItemManagerActor;
import ru.nemodev.runhero.entity.game.world.GroundInfinityActor;
import ru.nemodev.runhero.entity.game.world.SkyInfinityActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.GameStatus;
import ru.nemodev.runhero.scene.common.Box2dScene;
import ru.nemodev.runhero.util.Box2dObjectBuilder;
import ru.nemodev.runhero.util.SpriteUtils;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;
import static ru.nemodev.runhero.constant.GameConstant.METERS_Y;
import static ru.nemodev.runhero.constant.GameConstant.WORLD_UNIT;


/**
 * created by NemoDev on 06.05.2018 - 21:39
 */
public class GameScene extends Box2dScene
{
    private static final float HERO_WIDTH = WORLD_UNIT;
    private static final float HERO_HEIGHT = WORLD_UNIT;

    private static final float CAMERA_SHIFT = GameConstant.METERS_X / 2.5f;

    private Vector3 cameraPosition;

    private GroundInfinityActor groundInfinityActor;
    private SkyInfinityActor skyInfinityActor;

    private HeroActor heroActor;

    private MobManagerActor mobManagerActor;

    private ScoreItemManagerActor scoreItemManagerActor;

    public GameScene(World world, Viewport viewport, Batch batch)
    {
        super(world, viewport, batch);

        init();
    }

    private void init()
    {
        initHero();
        initScoreItem();
        initMob();
        initBorder();
    }

    @Override
    protected void doUpdate(float delta)
    {
        super.doUpdate(delta);

        // TODO камера дергается потому что тело героя дергается сука!!!
        Camera camera = getCamera();
        camera.position.x = heroActor.getHeroPosition().x + CAMERA_SHIFT * (GameManager.getInstance().isRightDirection() ? 1.f : -1.f);
    }

    private void initBorder()
    {
        final int platformCount = 3;
        Vector2 platformSize = new Vector2(METERS_X * 2.f, 0.001f);

        Array<Sprite> borderSprites = SpriteUtils.createList(
                BorderTextureConstant.GROUND_ATLAS,
                2.f, 1.f, new Vector2(0.f, 0.f));

        Sprite undoBorderSprite = SpriteUtils.create(BorderTextureConstant.GRASS_ATLAS, BorderTextureConstant.GRASS_GROUND,
                2.f, 0.35f, new Vector2(0.f, 0.f));

        groundInfinityActor = buildGroundPlatformActor(
                platformCount,
                new Vector2(METERS_X / 2.f, 1.f),
                platformSize, borderSprites, undoBorderSprite, new Contactable()
                {
                    @Override
                    public void beginContact(Contactable contactable)
                    {

                    }

                    @Override
                    public void endContact(Contactable contactable)
                    {

                    }

                    @Override
                    public ContactType getContactType()
                    {
                        return ContactType.GROUND;
                    }
                });
        addActor(groundInfinityActor);
        GameManager.getInstance().addMobEventListener(groundInfinityActor);

        skyInfinityActor = buildSkyPlatformActor(
                platformCount,
                new Vector2(METERS_X  / 2.f, METERS_Y),
                platformSize, borderSprites, new Contactable()
                {
                    @Override
                    public void beginContact(Contactable contactable)
                    {
                        if (contactable.getContactType() == ContactType.PLAYER && GameManager.getInstance().isRunning())
                        {
                            for (MobEventListener mobEventListener : GameManager.getInstance().getMobEventListeners())
                            {
                                mobEventListener.mobKillHero();
                            }
                            GameManager.getInstance().setGameStatus(GameStatus.GAME_OVER);
                        }
                    }

                    @Override
                    public void endContact(Contactable contactable)
                    {

                    }

                    @Override
                    public ContactType getContactType()
                    {
                        return ContactType.SKY;
                    }
                });
        addActor(skyInfinityActor);

    }

    private void initHero()
    {
        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;
        final Vector2 START_POSITION = new Vector2(
                0.f,
                METERS_Y - 1.f);
        final Vector2 START_VELOCITY = new Vector2(5.5f * direction, 0.f);

        Fixture heroFixture = Box2dObjectBuilder.createCircleFixture(world, ConstantBox2dBodyType.PLAYER, START_POSITION, HERO_WIDTH);
        Body heroBody = heroFixture.getBody();
        heroBody.setFixedRotation(true);
        heroBody.setGravityScale(2.f);
        heroBody.setLinearVelocity(START_VELOCITY);

        heroActor = new HeroActor(world, heroFixture,
                SpriteUtils.getHeroAnimations(), START_VELOCITY,
                HERO_WIDTH, HERO_HEIGHT);
        addActor(heroActor);

        GameManager.getInstance().addMobEventListener(heroActor);
    }

    private void initMob()
    {
        mobManagerActor = new MobManagerActor(world);
        addActor(mobManagerActor);

        GameManager.getInstance().addScoreChangeListener(mobManagerActor);
    }

    private void initScoreItem()
    {
        scoreItemManagerActor = new ScoreItemManagerActor(world);
        addActor(scoreItemManagerActor);

        GameManager.getInstance().addScoreChangeListener(scoreItemManagerActor);
    }

    private GroundInfinityActor buildGroundPlatformActor(int platformCount,
                                                         Vector2 platformStartPos, Vector2 platformSize,
                                                         Array<Sprite> borderSprites, Sprite undoBorderSprite, Contactable contactable)
    {
        GroundInfinityActor groundInfinityActor = new GroundInfinityActor(world, platformStartPos, platformSize, borderSprites, undoBorderSprite, platformCount);
        groundInfinityActor.setContactable(contactable);

        return groundInfinityActor;
    }

    private SkyInfinityActor buildSkyPlatformActor(int platformCount,
                                                   Vector2 platformStartPos, Vector2 platformSize,
                                                   Array<Sprite> borderSprites, Contactable contactable)
    {
        SkyInfinityActor skyInfinityActor = new SkyInfinityActor(world, platformStartPos, platformSize, borderSprites, platformCount);
        skyInfinityActor.setContactable(contactable);

        return skyInfinityActor;
    }

    @Override
    public boolean isInputController()
    {
        return true;
    }

    @Override
    public void dispose()
    {
        super.dispose();
    }

    @Override
    public void pause()
    {
        super.pause();
        cameraPosition = new Vector3(getCamera().position);
    }

    @Override
    public void show()
    {
        super.show();

        cameraPosition = new Vector3(getCamera().position);
    }

    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);

        getCamera().position.x = cameraPosition.x;
        getCamera().position.y = cameraPosition.y;
        getCamera().update();
    }
}
