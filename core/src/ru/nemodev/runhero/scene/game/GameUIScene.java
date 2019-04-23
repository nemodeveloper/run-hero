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
        float shiftX = 0.5f;

        float positionX = shiftX;

        Sprite pauseSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.GAME_PAUSE_BUTTON,
                pauseSize, pauseSize,
                positionX, GameConstant.METERS_Y - pauseSize / 2.f);

        pauseButton = new PauseButton(pauseSprite, pauseSprite,
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
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.MENU_BUTTON_START,
                buttonSize, buttonSize, basePosX, basePosY);

        ButtonActor startGameButton = new PauseButton(startGameSprite, startGameSprite,
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
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.MENU_BUTTON_MENU,
                buttonSize, buttonSize, basePosX, basePosY - buttonSize);

        ButtonActor menuButton = new ButtonActor(menuGameSprite, menuGameSprite)
        {
            @Override
            public boolean touchDown(float x, float y)
            {
                GameManager.getInstance().flushGame();
                GameManager.getInstance().getScreenManager().popScreen();
                GameManager.getInstance().getScreenManager().pushScreen(new MainScreen());

                return true;
            }
        };

        Sprite soundOnSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.MENU_BUTTON_SOUND_ON,
                buttonSize, buttonSize, basePosX - buttonSize, basePosY - buttonSize);

        MenuSoundButton menuSoundButton = new MenuSoundButton(soundOnSprite, soundOnSprite, soundEventListener);

        Sprite ratingSprite = SpriteUtils.create(
                UITextureConstant.COMMON_UI_ATLAS, UITextureConstant.MENU_BUTTON_RATING,
                buttonSize, buttonSize, basePosX + buttonSize, basePosY - buttonSize);
        RatingButton ratingButton = new RatingButton(ratingSprite, ratingSprite);

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
