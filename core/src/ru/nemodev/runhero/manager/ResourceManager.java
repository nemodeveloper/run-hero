package ru.nemodev.runhero.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.Set;

/**
 * created by NemoDev on 08.05.2018 - 20:06
 */
public final class ResourceManager implements Disposable
{
    private static volatile ResourceManager instance = new ResourceManager();

    private final AssetManager assetManager;

    private ResourceManager()
    {
        this.assetManager = new AssetManager();
    }

    public static ResourceManager getInstance()
    {
        return instance;
    }

    public void dispose()
    {
        assetManager.dispose();
    }

    public boolean isFinishLoad()
    {
        return assetManager.update();
    }

    public void loadAtlas(Set<String> atlasNames)
    {
        loadAssets(atlasNames, TextureAtlas.class);
    }

    private TextureAtlas getTextureAtlas(String atlasName)
    {
        return assetManager.get(atlasName, TextureAtlas.class);
    }

    public Sprite getSpriteWithoutWait(String filename)
    {
        return new Sprite(new Texture(Gdx.files.internal(filename)));
    }

    public Sprite getSprite(String atlasName, String spriteName)
    {
        return getTextureAtlas(atlasName).createSprite(spriteName);
    }

    public Array<Sprite> getSprites(String atlasName)
    {
        return getTextureAtlas(atlasName).createSprites();
    }

    public Skin getSkin(String fileName)
    {
        return assetManager.get(fileName, Skin.class);
    }

    public void loadSkin(Set<String> fileNames)
    {
        loadAssets(fileNames, Skin.class);
    }

    private void loadAssets(Set<String> fileNames, Class clazz)
    {
        for (String fileName : fileNames)
        {
            assetManager.load(fileName, clazz);
        }
    }

}
