package ru.nemodev.runhero.manager.pool;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.BackgroundTextureConstant;
import ru.nemodev.runhero.core.manager.resource.FontManager;
import ru.nemodev.runhero.core.util.SpriteUtils;
import ru.nemodev.runhero.entity.game.ScoreViewActor;
import ru.nemodev.runhero.entity.game.background.BackgroundActor;
import ru.nemodev.runhero.entity.game.background.GrassActor;
import ru.nemodev.runhero.entity.game.background.StarManagerActor;
import ru.nemodev.runhero.entity.game.background.TreeManagerActor;


public final class SimplePool
{
    public static final Pool<BackgroundActor> backgroundActorPool = new Pool<BackgroundActor>(1, 1)
    {
        @Override
        protected BackgroundActor newObject()
        {
            return new BackgroundActor(
                    SpriteUtils.create(BackgroundTextureConstant.BACKGROUND_ATLAS, BackgroundTextureConstant.BACKGROUND,
                            1.f, GameConstant.METERS_Y,
                            GameConstant.METERS_X / 2.f, GameConstant.METERS_Y / 2.f));
        }
    };

    public static final Pool<StarManagerActor> starManagerActorPool = new Pool<StarManagerActor>(1, 1)
    {
        @Override
        protected StarManagerActor newObject()
        {
            return new StarManagerActor(
                    SpriteUtils.create(BackgroundTextureConstant.STARS_BACKGROUND_ATLAS, BackgroundTextureConstant.STARS,
                            GameConstant.METERS_X, 6.f,
                            GameConstant.METERS_X / 2.f, GameConstant.METERS_Y / 2.f));
        }
    };

    public static final Pool<GrassActor> grassBackgroundActorPool = new Pool<GrassActor>(1, 1)
    {
        @Override
        protected GrassActor newObject()
        {
            return new GrassActor(
                    SpriteUtils.create(BackgroundTextureConstant.GRASS_BACKGROUND_ATLAS, BackgroundTextureConstant.GRASS_BACKGROUND,
                            GameConstant.METERS_X, 3.f,
                            0.f, 0.f));
        }
    };

    public static final Pool<TreeManagerActor> treeBackgroundActorPool = new Pool<TreeManagerActor>(1, 1)
    {
        @Override
        protected TreeManagerActor newObject()
        {
            Array<Sprite> enableTreeSprites = new Array<Sprite>(4);

            enableTreeSprites.add(buildTreeSprite(BackgroundTextureConstant.TREE_BACKGROUND_ATLAS, BackgroundTextureConstant.TREE_1
                    , new Vector2(GameConstant.WORLD_UNIT * 3.5f, 5.f), new Vector2(0, 1.f + 2.f)));

            enableTreeSprites.add(buildTreeSprite(BackgroundTextureConstant.TREE_BACKGROUND_ATLAS, BackgroundTextureConstant.TREE_2
                    , new Vector2(GameConstant.WORLD_UNIT * 4.f, 4.f), new Vector2(0, 1.f + 2.f)));

            enableTreeSprites.add(buildTreeSprite(BackgroundTextureConstant.TREE_BACKGROUND_ATLAS, BackgroundTextureConstant.TREE_3
                    , new Vector2(GameConstant.WORLD_UNIT * 3.5f, 6.f), new Vector2(0, 1.f + 3.f)));

            enableTreeSprites.add(buildTreeSprite(BackgroundTextureConstant.TREE_BACKGROUND_ATLAS, BackgroundTextureConstant.TREE_4
                    , new Vector2(GameConstant.WORLD_UNIT * 4.f, 4.5f), new Vector2(0, 1.f + 2.25f)));

            return new TreeManagerActor(enableTreeSprites);
        }
    };

    private static Sprite buildTreeSprite(String atlasName, String textureName, Vector2 size, Vector2 position)
    {
        return SpriteUtils.create(atlasName, textureName, size.x, size.y, position.x, position.y);
    }


    public static final Pool<ScoreViewActor> scoreActorPool = new Pool<ScoreViewActor>(1, 1)
    {
        @Override
        protected ScoreViewActor newObject()
        {
            return new ScoreViewActor(FontManager.getInstance().getCommonFont());
        }
    };

}
