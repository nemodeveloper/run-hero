package ru.nemodev.runhero.scene.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.scene.common.BaseScene;

public class GameUIScene extends BaseScene
{
    public GameUIScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);

        init();
    }

    private void init()
    {

    }

    @Override
    public boolean isInputController()
    {
        return true;
    }
}
