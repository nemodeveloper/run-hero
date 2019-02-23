package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.constant.texture.UITextureConstant;
import ru.nemodev.runhero.entity.game.ui.PauseButton;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.scene.common.BaseScene;
import ru.nemodev.runhero.util.SpriteUtils;

public class GameUIScene extends BaseScene
{
    private PauseButton pauseButton;

    public GameUIScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);

        init();
    }

    private void init()
    {
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

        pauseButton = new PauseButton(new SpriteDrawable(pauseSprite),
                positionX, GameConstant.METERS_Y - 1.5f, pauseSize);

        addActor(pauseButton);
    }

    @Override
    public boolean isInputController()
    {
        return true;
    }
}
