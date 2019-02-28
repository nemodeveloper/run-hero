package ru.nemodev.runhero.scene.main;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.SoundConstant;
import ru.nemodev.runhero.constant.texture.UITextureConstant;
import ru.nemodev.runhero.entity.common.SoundEventListener;
import ru.nemodev.runhero.entity.main.ExitGameButton;
import ru.nemodev.runhero.entity.main.MenuSoundButton;
import ru.nemodev.runhero.entity.main.StartGameButton;
import ru.nemodev.runhero.manager.resource.SoundManager;
import ru.nemodev.runhero.manager.system.ConfigManager;
import ru.nemodev.runhero.scene.common.BaseScene;
import ru.nemodev.runhero.util.SpriteUtils;

public class MainMenuScene extends BaseScene
{
    private StartGameButton startGameButton;
    private ExitGameButton exitGameButton;

    private MenuSoundButton menuSoundButton;
    private Music mainMenuMusic;

    public MainMenuScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);

        init();
    }

    private void init()
    {
        initStartButton();
        initExitButton();

        initMenuMusic();
    }

    private void initStartButton()
    {
        float sizeX = 3.f;
        float sizeY = 1.f;

        float positionX = GameConstant.METERS_X / 2.f;
        float positionY = GameConstant.METERS_Y / 2.f;

        Sprite startSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.MENU_START_BUTTON,
                sizeX, sizeY);

        startGameButton = new StartGameButton(new SpriteDrawable(startSprite),
                positionX, positionY, sizeX, sizeY);

        addActor(startGameButton);
    }

    private void initExitButton()
    {
        float sizeX = 3.f;
        float sizeY = 1.f;

        float positionX = GameConstant.METERS_X / 2.f;
        float positionY = GameConstant.METERS_Y / 2.f - 1.5f;

        Sprite exitSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.MENU_EXIT_BUTTON,
                sizeX, sizeY);

        exitGameButton = new ExitGameButton(new SpriteDrawable(exitSprite),
                positionX, positionY, sizeX, sizeY);

        addActor(exitGameButton);
    }

    private void initMenuMusic()
    {
        mainMenuMusic = SoundManager.getInstance().getMusic(SoundConstant.MAIN_THEME_MUSIC, true);
        if (ConfigManager.getInstance().isEnableSound())
        {
            mainMenuMusic.play();
        }

        float size = 2.f;

        float positionX = 2.f;
        float positionY = GameConstant.METERS_Y / 2.f - 3.5f;

        Sprite soundSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.MENU_EXIT_BUTTON,
                size, size);
        menuSoundButton = new MenuSoundButton(new SpriteDrawable(soundSprite), new SpriteDrawable(soundSprite),
                new SoundEventListener()
                {
                    @Override
                    public void soundEnable()
                    {
                        mainMenuMusic.play();
                    }

                    @Override
                    public void soundDisable()
                    {
                        mainMenuMusic.stop();
                    }
                },
                positionX, positionY, size, size);

        addActor(menuSoundButton);

    }

    @Override
    public boolean isInputController()
    {
        return true;
    }

    @Override
    public void dispose()
    {
        super.dispose();
    }
}
