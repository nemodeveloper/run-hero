package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.BackgroundTextureConstant;
import ru.nemodev.runhero.entity.game.background.BackgroundActor;
import ru.nemodev.runhero.entity.game.background.GrassActor;
import ru.nemodev.runhero.entity.game.background.MoonActor;
import ru.nemodev.runhero.entity.game.background.StarManagerActor;
import ru.nemodev.runhero.entity.game.background.TreeManagerActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.PoolManager;
import ru.nemodev.runhero.scene.common.BaseScene;
import ru.nemodev.runhero.util.SpriteUtils;

public class GameBackgroundScene extends BaseScene
{
    private BackgroundActor backgroundActor;
    private GrassActor grassActor;
    private MoonActor moonActor;
    private TreeManagerActor treeActor;
    private StarManagerActor starManagerActor;

    public GameBackgroundScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);
    }

    @Override
    public void init()
    {
        super.init();

        this.backgroundActor = PoolManager.getInstance().get(BackgroundActor.class);
        addActor(backgroundActor);

        this.starManagerActor = PoolManager.getInstance().get(StarManagerActor.class);
        addActor(starManagerActor);

        moonActor = getMoonActor();
        addActor(moonActor);

        treeActor = PoolManager.getInstance().get(TreeManagerActor.class);
        addActor(treeActor);

        grassActor = PoolManager.getInstance().get(GrassActor.class);
        addActor(grassActor);
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

    private MoonActor getMoonActor()
    {
        float moonSize = GameConstant.WORLD_UNIT * 3.f;
        float borderShift = 0.5f;

        float positionX = GameManager.getInstance().isRightDirection()
                ? GameConstant.METERS_X - moonSize - borderShift
                : borderShift;

        Sprite moonSprite = SpriteUtils.create(BackgroundTextureConstant.MOON_BACKGROUND_ATLAS, BackgroundTextureConstant.MOON
                , moonSize, moonSize, new Vector2(positionX, GameConstant.METERS_Y - moonSize - borderShift));

        return new MoonActor(moonSprite);
    }
}
