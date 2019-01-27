package ru.nemodev.runhero.entity.main;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Pool;

import ru.nemodev.runhero.entity.common.BackgroundActor;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.screen.play.GameScreen;
import ru.nemodev.runhero.util.InputUtils;
import ru.nemodev.runhero.util.ScreenUtils;


/**
 * created by NemoDev on 06.05.2018 - 20:13
 */
public class TutorialActor extends BackgroundActor implements Pool.Poolable
{
    public TutorialActor(Sprite howPlaySprite)
    {
        super(howPlaySprite, ScreenUtils.getWidth(), ScreenUtils.getHeight());
        addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                InputUtils.vibrate(250);
                GameManager.getInstance().getScreenManager().popAndPushScreen(new GameScreen());
                return true;
            }
        });
    }

    @Override
    public void clear()
    {
        super.clear();
        this.background.getTexture().dispose();
    }
}
