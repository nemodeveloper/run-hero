package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.UITextureConstant;
import ru.nemodev.runhero.entity.game.ui.GamePauseListener;
import ru.nemodev.runhero.entity.game.ui.PauseButton;
import ru.nemodev.runhero.entity.game.ui.PausePopUpActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.scene.common.BaseScene;
import ru.nemodev.runhero.util.SpriteUtils;

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

        float positionX = GameManager.getInstance().isRightDirection()
                ? shiftX
                : GameConstant.METERS_X - pauseSize - shiftX;

        Sprite pauseSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.GAME_PAUSE_BUTTON,
                pauseSize, pauseSize);

        pauseButton = new PauseButton(new SpriteDrawable(pauseSprite), new GamePauseListener()
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
        }, positionX, GameConstant.METERS_Y - 1.5f, pauseSize);

        addActor(pauseButton);
    }

    private void initPausePopUpActor()
    {
        Sprite backgroundPauseSprite = SpriteUtils.create(
                UITextureConstant.BUTTONS_UI_ATLAS, UITextureConstant.GAME_PAUSE_POP_UP_BACKGROUND,
                10.f, 6.f, new Vector2(GameConstant.METERS_X / 2.f, GameConstant.METERS_Y / 2.f));

        pausePopUpActor = new PausePopUpActor(backgroundPauseSprite);

        addActor(pausePopUpActor);
    }

    @Override
    public boolean isInputController()
    {
        return true;
    }
}
