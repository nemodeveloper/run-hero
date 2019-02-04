package ru.nemodev.runhero.manager;

import com.badlogic.gdx.Screen;

/**
 * created by NemoDev on 06.05.2018 - 19:42
 */
public interface ScreenManager
{
    /**
     * Установить активный экран в стек без очистки предыдущего
     * @param screen - экран
     */
    void pushScreen(Screen screen);

    /**
     * Удалить текущий экран из стека
     */
    void popScreen();

}
