package ru.nemodev.runhero.scene.main;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.BackgroundTextureConstant;
import ru.nemodev.runhero.constant.texture.BorderTextureConstant;
import ru.nemodev.runhero.core.scene.BaseScene;
import ru.nemodev.runhero.core.util.SpriteUtils;
import ru.nemodev.runhero.entity.game.background.BackgroundActor;
import ru.nemodev.runhero.entity.game.background.GrassActor;
import ru.nemodev.runhero.entity.game.background.MoonActor;
import ru.nemodev.runhero.entity.game.background.StarManagerActor;
import ru.nemodev.runhero.entity.game.background.TreeManagerActor;
import ru.nemodev.runhero.entity.game.border.GroundActor;
import ru.nemodev.runhero.manager.pool.PoolManager;


/**
 * created by NemoDev on 06.05.2018 - 21:39
 */
public class MainBackgroundScene extends BaseScene
{
    private BackgroundActor backgroundActor;
    private StarManagerActor starManagerActor;
    private MoonActor moonActor;

    private TreeManagerActor treeActor;
    private GrassActor grassActor;

    private World mockWorld;

    public MainBackgroundScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);

        init();
    }

    public void init()
    {
        backgroundActor = PoolManager.getInstance().get(BackgroundActor.class);
        addGameObject(backgroundActor);

        starManagerActor = PoolManager.getInstance().get(StarManagerActor.class);
        addGameObject(starManagerActor);

        moonActor = getMoonActor();
        addGameObject(moonActor);

        treeActor = PoolManager.getInstance().get(TreeManagerActor.class);
        addGameObject(treeActor);

        grassActor = PoolManager.getInstance().get(GrassActor.class);
        addGameObject(grassActor);

        mockWorld = new World(new Vector2(0.f, -9.81f), false);
        initWordObjects();
    }

    @Override
    public void dispose()
    {
        super.dispose();

        PoolManager.getInstance().free(backgroundActor);
        PoolManager.getInstance().free(starManagerActor);
        PoolManager.getInstance().free(treeActor);
        PoolManager.getInstance().free(grassActor);

        mockWorld.dispose();
    }

    private MoonActor getMoonActor()
    {
        float moonSize = GameConstant.WORLD_UNIT * 4.f;
        float borderShift = 0.5f;

        float positionX = GameConstant.METERS_X - moonSize / 2.f - borderShift;

        Sprite moonSprite = SpriteUtils.create(BackgroundTextureConstant.MOON_BACKGROUND_ATLAS, BackgroundTextureConstant.MOON
                , moonSize, moonSize, positionX, GameConstant.METERS_Y - moonSize / 2.f);

        return new MoonActor(moonSprite);
    }

    private void initWordObjects()
    {
        Array<Sprite> borderSprites = SpriteUtils.createList(
                BorderTextureConstant.GROUND_ATLAS,
                2.f, 1.f, new Vector2(0.f, 0.f));

        Sprite undoBorderSprite = SpriteUtils.create(BorderTextureConstant.GRASS_ATLAS, BorderTextureConstant.GRASS_GROUND,
                2.f, 0.35f);

        addGameObject(GroundActor.buildGroundActor(mockWorld,
                new Vector2(GameConstant.METERS_X / 2.f, 0.f),
                new Vector2(GameConstant.METERS_X, 0.001f), borderSprites, undoBorderSprite, 2.f));
    }

}
