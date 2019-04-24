package ru.nemodev.runhero.scene.main;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.SoundConstant;
import ru.nemodev.runhero.constant.texture.UITextureConstant;
import ru.nemodev.runhero.core.listener.SoundEventListener;
import ru.nemodev.runhero.core.manager.resource.SoundManager;
import ru.nemodev.runhero.core.manager.system.ConfigManager;
import ru.nemodev.runhero.core.scene.BaseScene;
import ru.nemodev.runhero.core.util.SpriteUtils;
import ru.nemodev.runhero.entity.main.ExitGameButton;
import ru.nemodev.runhero.entity.main.MenuSoundButton;
import ru.nemodev.runhero.entity.main.RatingButton;
import ru.nemodev.runhero.entity.main.StartGameButton;

public class MainMenuScene extends BaseScene
{
    private StartGameButton startGameButton;
    private ExitGameButton exitGameButton;

    private MenuSoundButton menuSoundButton;
    private Music mainMenuMusic;

    private RatingButton ratingButton;

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

        initRatingButton();
    }

    private void initStartButton()
    {
        float sizeX = 2.f;
        float sizeY = 2.f;

        float positionX = GameConstant.METERS_X / 2.f;
        float positionY = GameConstant.METERS_Y / 2.f + 0.5f;

        Sprite startSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_START,
                sizeX, sizeY, positionX, positionY);

        Sprite startSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_START_TOUCHED,
                sizeX, sizeY, positionX, positionY);

        startGameButton = new StartGameButton(startSprite, startSpriteTouched);

        addGameObject(startGameButton);
    }

    private void initExitButton()
    {
        float sizeX = 2.f;
        float sizeY = 2.f;

        float positionX = GameConstant.METERS_X / 2.f;
        float positionY = GameConstant.METERS_Y / 2.f - 1.5f;

        Sprite exitSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_EXIT,
                sizeX, sizeY, positionX, positionY);

        Sprite exitSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_EXIT_TOUCHED,
                sizeX, sizeY, positionX, positionY);

        exitGameButton = new ExitGameButton(exitSprite, exitSpriteTouched);

        addGameObject(exitGameButton);
    }

    private void initMenuMusic()
    {
        mainMenuMusic = SoundManager.getInstance().getMusic(SoundConstant.MAIN_THEME_MUSIC, true);
        if (ConfigManager.getInstance().isEnableSound())
        {
            mainMenuMusic.play();
        }

        float size = 2.f;

        float positionX = GameConstant.METERS_X / 2.f - size;
        float positionY = GameConstant.METERS_Y / 2.f - 1.5f;

        Sprite soundOnSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_SOUND_ON,
                size, size, positionX, positionY);
        Sprite soundOnSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_SOUND_ON_TOUCHED,
                size, size, positionX, positionY);

        Sprite soundOffSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_SOUND_OFF,
                size, size, positionX, positionY);
        Sprite soundOffSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_SOUND_OFF_TOUCHED,
                size, size, positionX, positionY);

        menuSoundButton = new MenuSoundButton(soundOnSprite, soundOnSpriteTouched,
                soundOffSprite, soundOffSpriteTouched,
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
                });

        addGameObject(menuSoundButton);

    }

    private void initRatingButton()
    {
        float size = 2.f;

        float positionX = GameConstant.METERS_X / 2.f + size;
        float positionY = GameConstant.METERS_Y / 2.f - 1.5f;

        Sprite ratingSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_RATING,
                size, size, positionX, positionY);

        Sprite ratingSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_RATING_TOUCHED,
                size, size, positionX, positionY);

        ratingButton = new RatingButton(ratingSprite, ratingSpriteTouched);

        addGameObject(ratingButton);
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
