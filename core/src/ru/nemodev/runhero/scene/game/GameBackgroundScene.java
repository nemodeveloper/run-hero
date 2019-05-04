package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.BackgroundTextureConstant;
import ru.nemodev.runhero.core.scene.BaseScene;
import ru.nemodev.runhero.core.util.SpriteUtils;
import ru.nemodev.runhero.entity.game.background.BackgroundActor;
import ru.nemodev.runhero.entity.game.background.GrassActor;
import ru.nemodev.runhero.entity.game.background.MoonActor;
import ru.nemodev.runhero.entity.game.background.StarManagerActor;
import ru.nemodev.runhero.entity.game.background.TreeManagerActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.pool.PoolManager;

public class GameBackgroundScene extends BaseScene
{
    private BackgroundActor backgroundActor;
    private GrassActor grassActor;
    private MoonActor moonActor;
    private TreeManagerActor treeActor;
    private StarManagerActor starManagerActor;

    public GameBackgroundScene(Batch batch)
    {
        super(batch);

        init();
    }

    private void init()
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
    }

    @Override
    public void dispose()
    {
        super.dispose();

        PoolManager.getInstance().free(backgroundActor);
        PoolManager.getInstance().free(starManagerActor);
        PoolManager.getInstance().free(treeActor);
        PoolManager.getInstance().free(grassActor);
    }

    @Override
    protected boolean isNeedUpdate()
    {
        return !GameManager.getInstance().isPause();
    }

    private MoonActor getMoonActor()
    {
        float moonSize = GameConstant.WORLD_UNIT * 4.f;
        float borderShift = 0.5f;

        float positionX = GameManager.getInstance().isRightDirection()
                ? GameConstant.METERS_X - moonSize / 2.f - borderShift
                : moonSize / 2.f + borderShift;

        Sprite moonSprite = SpriteUtils.create(BackgroundTextureConstant.MOON_BACKGROUND_ATLAS, BackgroundTextureConstant.MOON
                , moonSize, moonSize, positionX, GameConstant.METERS_Y - moonSize / 2.f);

        return new MoonActor(moonSprite);
    }
}
