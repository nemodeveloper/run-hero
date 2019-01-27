package ru.nemodev.runhero.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

import ru.nemodev.runhero.manager.GameManager;

/**
 * created by NemoDev on 08.05.2018 - 21:24
 */
public final class InputUtils
{
    public static final InputProcessor backInputProcessor = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {

            if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK))
            {
                GameManager.getInstance().exit();
            }
            return false;
        }
    };

    private InputUtils() { }

    public static void setInputProcessor(InputProcessor inputProcessor)
    {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public static void setCatchBackButton()
    {
        Gdx.input.setCatchBackKey(true);
    }

    public static void vibrate(int milliseconds)
    {
        Gdx.input.vibrate(milliseconds);
    }

}
