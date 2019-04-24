package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.UITextureConstant;
import ru.nemodev.runhero.core.listener.SoundEventListener;
import ru.nemodev.runhero.core.model.ButtonActor;
import ru.nemodev.runhero.core.scene.BaseScene;
import ru.nemodev.runhero.core.util.SpriteUtils;
import ru.nemodev.runhero.entity.game.ui.GamePauseListener;
import ru.nemodev.runhero.entity.game.ui.PauseButton;
import ru.nemodev.runhero.entity.game.ui.PausePopUpActor;
import ru.nemodev.runhero.entity.main.MenuSoundButton;
import ru.nemodev.runhero.entity.main.RatingButton;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.screen.main.MainScreen;

public class GameUIScene extends BaseScene
{
    private PauseButton pauseButton;
    private PausePopUpActor pausePopUpActor;
    private final SoundEventListener soundEventListener;

    public GameUIScene(Viewport viewport, Batch batch, SoundEventListener soundEventListener)
    {
        super(viewport, batch);
        this.soundEventListener = soundEventListener;

        init();
    }

    private void init()
    {
        initPausePopUpActor();
        initPauseButton();
    }

    private void initPauseButton()
    {
        float pauseSize = 1.f;
        float shift = pauseSize * 0.75f;

        Sprite pauseSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_PAUSE,
                pauseSize, pauseSize,
                shift, GameConstant.METERS_Y - shift);

        Sprite pauseSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_PAUSE_TOUCHED,
                pauseSize, pauseSize,
                shift, GameConstant.METERS_Y - shift);

        pauseButton = new PauseButton(pauseSprite, pauseSpriteTouched,
                new GamePauseListener()
                {
                    @Override
                    public void pauseStart()
                    {
                        pausePopUpActor.setVisible(true);
                    }

                    @Override
                    public void pauseEnd()
                    {
                        pausePopUpActor.setVisible(false);
                    }
                });

        addGameObject(pauseButton);
    }

    private void initPausePopUpActor()
    {
        final float basePosX = GameConstant.METERS_X / 2.f;
        final float basePosY = GameConstant.METERS_Y / 2.f;

        Sprite backgroundPauseSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.GAME_STATUS_BOARD,
                8.f, 8.f, basePosX, basePosY);

        pausePopUpActor = new PausePopUpActor(backgroundPauseSprite);

        final float buttonSize = 1.6f;

        Sprite startGameSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_START,
                buttonSize, buttonSize, basePosX, basePosY);

        Sprite startGameSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_START_TOUCHED,
                buttonSize, buttonSize, basePosX, basePosY);

        ButtonActor startGameButton = new PauseButton(startGameSprite, startGameSpriteTouched,
                new GamePauseListener()
                {
                    @Override
                    public void pauseStart()
                    {
                        pausePopUpActor.setVisible(true);
                    }

                    @Override
                    public void pauseEnd()
                    {
                        pausePopUpActor.setVisible(false);
                    }
                });

        Sprite menuGameSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_MENU,
                buttonSize, buttonSize, basePosX, basePosY - buttonSize);

        Sprite menuGameSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_MENU_TOUCHED,
                buttonSize, buttonSize, basePosX, basePosY - buttonSize);

        ButtonActor menuButton = new ButtonActor(menuGameSprite, menuGameSpriteTouched)
        {
            @Override
            public void doTouchUp(float x, float y)
            {
                GameManager.getInstance().flushGame();
                GameManager.getInstance().getScreenManager().popScreen();
                GameManager.getInstance().getScreenManager().pushScreen(new MainScreen());
            }
        };

        Sprite soundOnSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_SOUND_ON,
                buttonSize, buttonSize, basePosX - buttonSize, basePosY - buttonSize);

        Sprite soundOnSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_SOUND_ON_TOUCHED,
                buttonSize, buttonSize, basePosX - buttonSize, basePosY - buttonSize);

        Sprite soundOffSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_SOUND_OFF,
                buttonSize, buttonSize, basePosX - buttonSize, basePosY - buttonSize);

        Sprite soundOffSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_SOUND_OFF_TOUCHED,
                buttonSize, buttonSize, basePosX - buttonSize, basePosY - buttonSize);

        MenuSoundButton menuSoundButton = new MenuSoundButton(soundOnSprite, soundOnSpriteTouched,
                soundOffSprite, soundOffSpriteTouched, soundEventListener);

        Sprite ratingSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_RATING,
                buttonSize, buttonSize, basePosX + buttonSize, basePosY - buttonSize);

        Sprite ratingSpriteTouched = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.BUTTON_RATING_TOUCHED,
                buttonSize, buttonSize, basePosX + buttonSize, basePosY - buttonSize);

        RatingButton ratingButton = new RatingButton(ratingSprite, ratingSpriteTouched);

        pausePopUpActor.addGameObject(startGameButton);
        pausePopUpActor.addGameObject(menuButton);
        pausePopUpActor.addGameObject(menuSoundButton);
        pausePopUpActor.addGameObject(ratingButton);
        addGameObject(pausePopUpActor);
    }

    @Override
    public boolean isInputController()
    {
        return true;
    }
}
