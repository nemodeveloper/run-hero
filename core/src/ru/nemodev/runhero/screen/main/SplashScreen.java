package ru.nemodev.runhero.screen.main;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import ru.nemodev.runhero.constant.texture.BackgroundTextureConstant;
import ru.nemodev.runhero.constant.texture.BorderTextureConstant;
import ru.nemodev.runhero.constant.texture.HeroTextureConstant;
import ru.nemodev.runhero.constant.texture.MobsAnimationTextureConstant;
import ru.nemodev.runhero.constant.texture.MobsStaticTextureConstant;
import ru.nemodev.runhero.constant.texture.ScoreItemTextureConstant;
import ru.nemodev.runhero.entity.play.background.BackgroundActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.PhysicManager;
import ru.nemodev.runhero.manager.ResourceManager;
import ru.nemodev.runhero.scene.common.BaseScene;
import ru.nemodev.runhero.screen.common.BaseLoaderScreen;
import ru.nemodev.runhero.util.ScreenUtils;
import ru.nemodev.runhero.util.SpriteUtils;

public class SplashScreen extends BaseLoaderScreen
{
    private final Sprite backgroundSprite;

    public SplashScreen()
    {
        super();

        BaseScene baseScene = new BaseScene(new ScreenViewport(), GameManager.getInstance().getSpriteBatch());

        backgroundSprite = SpriteUtils.create(BackgroundTextureConstant.SPLASH);
        backgroundSprite.setSize(ScreenUtils.getWidth(), ScreenUtils.getHeight());
        backgroundSprite.setPosition(0, 0);

        baseScene.addActor(new BackgroundActor(backgroundSprite));

        addScene(baseScene);
    }

    @Override
    protected void loadResources()
    {
        ResourceManager.getInstance().loadAtlas(BackgroundTextureConstant.ATLAS_FOR_LOAD);
        ResourceManager.getInstance().loadAtlas(BorderTextureConstant.ATLAS_FOR_LOAD);
        ResourceManager.getInstance().loadAtlas(HeroTextureConstant.ATLAS_FOR_LOAD);
        ResourceManager.getInstance().loadAtlas(ScoreItemTextureConstant.ATLAS_FOR_LOAD);

        ResourceManager.getInstance().loadAtlas(MobsStaticTextureConstant.ATLAS_FOR_LOAD);
        ResourceManager.getInstance().loadAtlas(MobsAnimationTextureConstant.ATLAS_FOR_LOAD);

        PhysicManager.getInstance();
    }

    @Override
    protected void doAfterLoadResource()
    {
        GameManager.getInstance().getScreenManager().popAndPushScreen(new MainScreen());
    }

    @Override
    public void dispose()
    {
        super.dispose();
        backgroundSprite.getTexture().dispose();
    }
}
