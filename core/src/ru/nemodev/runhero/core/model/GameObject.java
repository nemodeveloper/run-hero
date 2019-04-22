package ru.nemodev.runhero.core.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;

import ru.nemodev.runhero.core.scene.Scene;

public interface GameObject extends Touchable, Disposable
{
    void update(float delta);
    void draw(Batch batch, float parentAlpha);

    boolean isVisible();

    boolean isNeedRemove();
    void remove();

    Scene getScene();
    void setScene(Scene scene);
}
