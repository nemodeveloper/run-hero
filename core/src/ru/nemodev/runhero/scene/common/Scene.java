package ru.nemodev.runhero.scene.common;

import com.badlogic.gdx.utils.Disposable;

public interface Scene extends Disposable
{
    void init();

    void show();

    void render(float delta);

    void resize(int width, int height);
}
