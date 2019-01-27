package ru.nemodev.runhero.manager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.BackgroundTextureConstant;
import ru.nemodev.runhero.entity.common.BackgroundActor;
import ru.nemodev.runhero.entity.play.ScoreViewActor;
import ru.nemodev.runhero.entity.play.background.GrassActor;
import ru.nemodev.runhero.entity.play.background.TreeActor;
import ru.nemodev.runhero.entity.play.background.TreeManagerActor;
import ru.nemodev.runhero.util.ScreenUtils;
import ru.nemodev.runhero.util.SpriteUtils;


public final class SimplePool
{
    public static final Pool<BackgroundActor> backgroundActorPool = new Pool<BackgroundActor>(1)
    {
        @Override
        protected BackgroundActor newObject()
        {
            return new BackgroundActor(
                    SpriteUtils.create(BackgroundTextureConstant.BACKGROUND_ATLAS, BackgroundTextureConstant.BACKGROUND),
                    ScreenUtils.getWidth(), ScreenUtils.getHeight());
        }
    };

    public static final Pool<GrassActor> grassBackgroundActorPool = new Pool<GrassActor>(1)
    {
        @Override
        protected GrassActor newObject()
        {
            return new GrassActor(
                    SpriteUtils.create(BackgroundTextureConstant.GRASS_BACKGROUND_ATLAS, BackgroundTextureConstant.GRASS
                            , GameConstant.METERS_X, GameConstant.WORLD_UNIT * 2.5f, new Vector2(0.f, 0.f)));
        }
    };

    public static final Pool<TreeManagerActor> treeBackgroundActorPool = new Pool<TreeManagerActor>()
    {
        @Override
        protected TreeManagerActor newObject()
        {
            TreeManagerActor treeManagerActor = new TreeManagerActor();
            treeManagerActor.addActor(buildTreeActor(BackgroundTextureConstant.TREE_BACKGROUND_ATLAS, BackgroundTextureConstant.TREE_1
                , new Vector2(GameConstant.WORLD_UNIT * 3.5f, GameConstant.WORLD_UNIT * 5), new Vector2(0, 1.f)));

            treeManagerActor.addActor(buildTreeActor(BackgroundTextureConstant.TREE_BACKGROUND_ATLAS, BackgroundTextureConstant.TREE_2
                    , new Vector2(GameConstant.WORLD_UNIT * 4.f, GameConstant.WORLD_UNIT * 4), new Vector2(5, 1.f)));

            treeManagerActor.addActor(buildTreeActor(BackgroundTextureConstant.TREE_BACKGROUND_ATLAS, BackgroundTextureConstant.TREE_3
                    , new Vector2(GameConstant.WORLD_UNIT * 3.5f, GameConstant.WORLD_UNIT * 6), new Vector2(10, 1.f)));

            treeManagerActor.addActor(buildTreeActor(BackgroundTextureConstant.TREE_BACKGROUND_ATLAS, BackgroundTextureConstant.TREE_4
                    , new Vector2(GameConstant.WORLD_UNIT * 4.f, GameConstant.WORLD_UNIT * 4.5f), new Vector2(14, 1.f)));


            return treeManagerActor;
        }
    };

    private static TreeActor buildTreeActor(String atlasName, String textureName, Vector2 size, Vector2 position)
    {
        return new TreeActor(SpriteUtils.create(atlasName, textureName, size.x, size.y, position));
    }

    public static final Pool<ScoreViewActor> scoreActorPool = new Pool<ScoreViewActor>(1)
    {
        @Override
        protected ScoreViewActor newObject()
        {
            return new ScoreViewActor(FontManager.getInstance().getCommonFont());
        }
    };

}
