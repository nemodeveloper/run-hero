package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.BackgroundTextureConstant;
import ru.nemodev.runhero.entity.common.BackgroundActor;
import ru.nemodev.runhero.entity.play.background.GrassActor;
import ru.nemodev.runhero.entity.play.background.MoonActor;
import ru.nemodev.runhero.entity.play.background.TreeManagerActor;
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

        grassActor = PoolManager.getInstance().get(GrassActor.class);
        addActor(grassActor);

        moonActor = getMoonActor();
        addActor(moonActor);

        treeActor = PoolManager.getInstance().get(TreeManagerActor.class);
        addActor(treeActor);
    }

    @Override
    public void dispose()
    {
        super.dispose();

        PoolManager.getInstance().free(backgroundActor);
    }

    private MoonActor getMoonActor()
    {
        float moonSize = GameConstant.WORLD_UNIT * 3.f;

        Sprite moonSprite = SpriteUtils.create(BackgroundTextureConstant.MOON_BACKGROUND_ATLAS, BackgroundTextureConstant.MOON
                , moonSize, moonSize);

        float positionX = GameManager.getInstance().isRightDirection()
                ? GameConstant.METERS_X - moonSize - 1.f
                : 1.f;

        Vector2 position = new Vector2(positionX, GameConstant.METERS_Y - moonSize);

        return new MoonActor(moonSprite, position);
    }
}
