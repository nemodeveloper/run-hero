package ru.nemodev.runhero.screen.load;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.constant.SoundConstant;
import ru.nemodev.runhero.constant.physic.PhysicLoaderConstant;
import ru.nemodev.runhero.constant.texture.AtlasLoaderConstant;
import ru.nemodev.runhero.constant.texture.BackgroundTextureConstant;
import ru.nemodev.runhero.core.manager.resource.FontManager;
import ru.nemodev.runhero.core.manager.resource.PhysicManager;
import ru.nemodev.runhero.core.manager.resource.ResourceLoader;
import ru.nemodev.runhero.core.scene.BaseScene;
import ru.nemodev.runhero.core.screen.BaseLoaderScreen;
import ru.nemodev.runhero.core.util.SpriteUtils;
import ru.nemodev.runhero.entity.load.SplashActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.screen.main.MainScreen;

import static ru.nemodev.runhero.constant.GameConstant.HALF_X;
import static ru.nemodev.runhero.constant.GameConstant.HALF_Y;
import static ru.nemodev.runhero.constant.GameConstant.METERS_X;
import static ru.nemodev.runhero.constant.GameConstant.METERS_Y;

public class SplashScreen extends BaseLoaderScreen
{
    private final Sprite splashActor;

    public SplashScreen()
    {
        super();

        BaseScene baseScene = new BaseScene(GameManager.getInstance().getSpriteBatch());

        splashActor = SpriteUtils.create(BackgroundTextureConstant.SPLASH, METERS_X, METERS_Y, HALF_X, HALF_Y);

        baseScene.addGameObject(new SplashActor(splashActor));

        addScene(baseScene);
    }

    @Override
    protected void loadResources()
    {
        loadTexture();
        loadSound();
        loadPhysic();

        FontManager.getInstance();
    }

    private void loadTexture()
    {
        ResourceLoader.getInstance().loadAtlas(AtlasLoaderConstant.ATLAS_BODY_FOR_LOADING);
    }

    public void loadSound()
    {
        ResourceLoader.getInstance().loadMusic(SoundConstant.MUSIC_FOR_LOADING);
        ResourceLoader.getInstance().loadSound(SoundConstant.SOUND_FOR_LOADING);
    }

    public void loadPhysic()
    {
        PhysicManager.getInstance();
        ResourceLoader.getInstance().loadBodyEditorLoader(PhysicLoaderConstant.PHYSIC_BODY_FOR_LOADING);
    }

    @Override
    protected void doAfterLoadResource()
    {
        PhysicManager.getInstance().preparePhysicBodies();

        GameManager.getInstance().getScreenManager().popScreen();
        GameManager.getInstance().getScreenManager().pushScreen(new MainScreen());
    }

    @Override
    public void dispose()
    {
        super.dispose();
        splashActor.getTexture().dispose();
    }
}
