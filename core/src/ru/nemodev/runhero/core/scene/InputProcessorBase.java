package ru.nemodev.runhero.core.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.core.model.GameObject;

public abstract class InputProcessorBase implements InputProcessor
{
    protected final Viewport viewport;
    protected final Array<GameObject> gameObjects;

    private GameObject currentTouchHandler;
    private final Vector2 lastScreenTouch;

    protected InputProcessorBase(Viewport viewport, Array<GameObject> gameObjects)
    {
        this.viewport = viewport;
        this.gameObjects = gameObjects;

        this.lastScreenTouch = new Vector2();
    }

    private boolean isInsideViewport (int screenX, int screenY)
    {
        int x0 = viewport.getScreenX();
        int x1 = x0 + viewport.getScreenWidth();
        int y0 = viewport.getScreenY();
        int y1 = y0 + viewport.getScreenHeight();

        screenY = Gdx.graphics.getHeight() - 1 - screenY;

        return screenX >= x0 && screenX < x1 && screenY >= y0 && screenY < y1;
    }

    private Vector2 screenToSceneCoordinates(Vector2 screenCoords)
    {
        viewport.unproject(screenCoords);
        return screenCoords;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if (!isInsideViewport(screenX, screenY))
            return false;

        lastScreenTouch.set(screenX, screenY);
        screenToSceneCoordinates(lastScreenTouch);

        for (GameObject gameObject : new Array.ArrayIterator<GameObject>(gameObjects))
        {
            GameObject candidate = gameObject.isTouch(lastScreenTouch.x, lastScreenTouch.y);
            if (candidate != null && candidate.touchDown(lastScreenTouch.x, lastScreenTouch.y))
            {
                currentTouchHandler = candidate;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (currentTouchHandler != null)
        {
            lastScreenTouch.set(screenX, screenY);
            screenToSceneCoordinates(lastScreenTouch);

            currentTouchHandler.touchUp(lastScreenTouch.x, lastScreenTouch.y);
            currentTouchHandler = null;

            return true;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
