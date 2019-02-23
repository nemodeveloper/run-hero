package ru.nemodev.runhero.util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.dermetfan.gdx.graphics.g2d.AnimatedBox2DSprite;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;
import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import ru.nemodev.runhero.constant.texture.HeroTextureConstant;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.resource.ResourceLoader;

public final class SpriteUtils
{
    private static Array<AnimatedBox2DSprite> createHeroAnimation()
    {
        Array<AnimatedBox2DSprite> heroAnimation = new Array<AnimatedBox2DSprite>();
        heroAnimation.add(createAnimationBox2d(HeroTextureConstant.JELE_GREEN_ATLAS, 0.1f, Animation.PlayMode.LOOP));
        heroAnimation.add(createAnimationBox2d(HeroTextureConstant.JELE_YELLOW_ATLAS, 0.1f, Animation.PlayMode.LOOP));

        return heroAnimation;
    }

    private SpriteUtils() { }

    public static Sprite create(String textureName)
    {
        return ResourceLoader.getInstance().getSpriteWithoutWait(textureName);
    }

    public static Sprite create(String atlasName, String spiteName)
    {
        Sprite sprite = ResourceLoader.getInstance().getSprite(atlasName, spiteName);
        sprite.setX(0.f);
        sprite.setY(0.f);

        return sprite;
    }

    public static Sprite create(String atlasName, String textureName, float width, float height, Vector2 position)
    {
        final Sprite sprite = create(atlasName, textureName);
        setBounds(sprite, width, height, position);

        return sprite;
    }

    public static Sprite create(String atlasName, String textureName, float width, float height)
    {
        return create(atlasName, textureName, width, height, new Vector2(0.f, 0.f));
    }

    private static void setBounds(Sprite sprite, float width, float height, Vector2 position)
    {
        sprite.setBounds(position.x, position.y, width, height);
        sprite.setOriginCenter();
    }

    public static Array<Sprite> createList(String atlasName, float width, float height, Vector2 position)
    {
        Array<Sprite> sprites = ResourceLoader.getInstance().getSprites(atlasName);
        for (Sprite sprite : sprites)
        {
            setBounds(sprite, width, height, position);
        }

        return sprites;
    }

    public static Box2DSprite createBox2d(String atlasName, String textureName)
    {
        Box2DSprite box2DSprite = new Box2DSprite(create(atlasName, textureName));
        box2DSprite.setX(0.f);
        box2DSprite.setY(0.f);

//        if (!GameManager.getInstance().isRightDirection())
//        {
//            box2DSprite.flip(true, false);
//        }

        return box2DSprite;
    }

    public static Animation<TextureRegion> createAnimation(String atlasName, float frameDuration, Animation.PlayMode playMode)
    {
        Array<Sprite> sprites = ResourceLoader.getInstance().getSprites(atlasName);
        if (!GameManager.getInstance().isRightDirection())
        {
            for (Sprite sprite : sprites)
            {
                sprite.flip(true, false);
            }
        }

        return new Animation<TextureRegion>(frameDuration, sprites, playMode);
    }

    public static AnimatedSprite createAnimationSprite(String atlasName, float frameDuration, Animation.PlayMode playMode)
    {
        AnimatedSprite animatedSprite = new AnimatedSprite(createAnimation(atlasName, frameDuration, playMode));
        animatedSprite.setAutoUpdate(false);
        return animatedSprite;
    }

    public static AnimatedBox2DSprite createAnimationBox2d(String atlasName, float frameDuration, Animation.PlayMode playMode)
    {
        return new AnimatedBox2DSprite(createAnimationSprite(atlasName, frameDuration, playMode));
    }

    public static Array<AnimatedBox2DSprite> getHeroAnimations()
    {
        return createHeroAnimation();
    }

}
