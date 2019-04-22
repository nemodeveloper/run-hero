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
        float positionY = GameConstant.METERS_Y / 2.f + 1.f;

        Sprite startSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.MENU_BUTTON_START,
                sizeX, sizeY, positionX, positionY);

        startGameButton = new StartGameButton(startSprite, startSprite);

        addGameObject(startGameButton);
    }

    private void initExitButton()
    {
        float sizeX = 2.f;
        float sizeY = 2.f;

        float positionX = GameConstant.METERS_X / 2.f;
        float positionY = GameConstant.METERS_Y / 2.f - 1.5f;

        Sprite exitSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.MENU_BUTTON_EXIT,
                sizeX, sizeY, positionX, positionY);

        exitGameButton = new ExitGameButton(exitSprite, exitSprite);

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

        float positionX = GameConstant.METERS_X / 2.f - 3.5f;
        float positionY = GameConstant.METERS_Y / 2.f - 1.5f;

        Sprite soundOnSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.MENU_BUTTON_SOUND_ON,
                size, size, positionX, positionY);
        Sprite soundOffSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.MENU_BUTTON_SOUND_OFF,
                size, size, positionX, positionY);

        menuSoundButton = new MenuSoundButton(soundOnSprite, soundOffSprite,
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

        float positionX = GameConstant.METERS_X / 2.f + 3.5f;
        float positionY = GameConstant.METERS_Y / 2.f - 1.5f;

        Sprite ratingSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.MENU_BUTTON_RATING,
                size, size, positionX, positionY);
        ratingButton = new RatingButton(ratingSprite, ratingSprite);

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
