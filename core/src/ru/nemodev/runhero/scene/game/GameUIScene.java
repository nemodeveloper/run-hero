package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.UITextureConstant;
import ru.nemodev.runhero.core.scene.BaseScene;
import ru.nemodev.runhero.core.util.SpriteUtils;
import ru.nemodev.runhero.entity.game.ui.GamePauseListener;
import ru.nemodev.runhero.entity.game.ui.PauseButton;
import ru.nemodev.runhero.entity.game.ui.PausePopUpActor;

public class GameUIScene extends BaseScene
{
    private PauseButton pauseButton;
    private PausePopUpActor pausePopUpActor;

    public GameUIScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);

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
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.GAME_PAUSE_BUTTON,
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
        Sprite backgroundPauseSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.GAME_PAUSE_POP_UP_BACKGROUND,
                10.f, 6.f, GameConstant.METERS_X / 2.f, GameConstant.METERS_Y / 2.f);

        pausePopUpActor = new PausePopUpActor(backgroundPauseSprite);

        addGameObject(pausePopUpActor);
    }

    @Override
    public boolean isInputController()
    {
        return true;
    }
}
