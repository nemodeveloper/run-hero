package ru.nemodev.runhero.scene.main;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.constant.texture.BackgroundTextureConstant;
import ru.nemodev.runhero.entity.main.TutorialActor;
import ru.nemodev.runhero.scene.common.BaseScene;
import ru.nemodev.runhero.util.SpriteUtils;

/**
 * created by NemoDev on 06.05.2018 - 21:39
 */
public class TutorialScene extends BaseScene
{
    private TutorialActor tutorialActor;

    public TutorialScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);
    }

    @Override
    public void init()
    {
        super.init();

        Sprite howPlaySprite = SpriteUtils.create(BackgroundTextureConstant.HOW_PLAY);
        tutorialActor = new TutorialActor(howPlaySprite);
        addActor(tutorialActor);
    }

    @Override
    protected boolean isInputController()
    {
        return true;
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable)
    {
        return tutorialActor;
    }
}
