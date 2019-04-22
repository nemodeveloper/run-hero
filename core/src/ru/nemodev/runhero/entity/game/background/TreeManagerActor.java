package ru.nemodev.runhero.entity.game.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Iterator;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.core.model.BaseActor;
import ru.nemodev.runhero.manager.GameManager;

public class TreeManagerActor extends BaseActor implements Pool.Poolable
{
    private final Array<Sprite> enableTreeSprites;

    private final Array<Sprite> currentTrees;

    public TreeManagerActor(Array<Sprite> enableTreeSprites)
    {
        this.enableTreeSprites = enableTreeSprites;
        this.currentTrees = new Array<Sprite>(10);

        initTreePositions();
    }

    private void initTreePositions()
    {
        currentTrees.clear();

        boolean isRightDirection = GameManager.getInstance().isRightDirection();

        float startPositionX = isRightDirection ? 0.f : GameConstant.METERS_X;

        boolean isCanSpawnTree = true;

        while (isCanSpawnTree)
        {
            Sprite tree = getRandomTree();

            if (currentTrees.size == 0)
            {
                currentTrees.add(tree);
                tree.setX(startPositionX);

                continue;
            }

            spawnNewTreeSprite();
            startPositionX = currentTrees.get(currentTrees.size - 1).getX();

            if (isRightDirection && startPositionX >= GameConstant.METERS_X
                    || !isRightDirection && startPositionX <= 0.f)
            {
                isCanSpawnTree = false;
            }
        }

        spawnNewTreeSprite();
    }

    @Override
    protected boolean isNeedUpdate()
    {
        return GameManager.getInstance().isRunning();
    }

    @Override
    protected void doAct(float delta)
    {
        moveCurrentTrees(delta);
    }

    @Override
    protected void doDraw(Batch batch, float parentAlpha)
    {
        for (Sprite treeSprite : currentTrees)
        {
            treeSprite.draw(batch);
        }
    }

    private void moveCurrentTrees(float delta)
    {
        boolean isNeedNewTree = false;

        float shift = GameManager.getInstance().isRightDirection()
                ? -delta
                : delta;

        Iterator<Sprite> treeIterator = currentTrees.iterator();
        while (treeIterator.hasNext())
        {
            Sprite currentTree = treeIterator.next();

            currentTree.setX(currentTree.getX() + shift);

            if (GameManager.getInstance().isRightDirection() && currentTree.getX() + currentTree.getWidth() < 0.f
                    || !GameManager.getInstance().isRightDirection() && currentTree.getX() - currentTree.getWidth() > GameConstant.METERS_X)
            {
                treeIterator.remove();
                isNeedNewTree = true;
            }
        }

        if (isNeedNewTree)
        {
            spawnNewTreeSprite();
        }

    }

    private void spawnNewTreeSprite()
    {
        Sprite newTreeSprite = getRandomTree();
        Sprite lastSprite = currentTrees.get(currentTrees.size - 1);

        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;

        newTreeSprite.setX(lastSprite.getX() + direction * (newTreeSprite.getWidth() / 2.f) + direction * (lastSprite.getWidth() / 2.f) + direction);

        currentTrees.add(newTreeSprite);
    }

    private Sprite getRandomTree()
    {
        return new Sprite(enableTreeSprites.get(MathUtils.random(0, enableTreeSprites.size - 1)));
    }

    @Override
    public void reset()
    {
        initTreePositions();
        setScene(null);
    }
}
