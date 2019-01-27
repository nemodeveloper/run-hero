package ru.nemodev.runhero.manager;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import ru.nemodev.runhero.entity.common.BackgroundActor;
import ru.nemodev.runhero.entity.play.ScoreViewActor;
import ru.nemodev.runhero.entity.play.background.GrassActor;
import ru.nemodev.runhero.entity.play.background.TreeManagerActor;

/**
 * created by NemoDev on 08.05.2018 - 20:45
 */
public final class PoolManager
{
    private static volatile PoolManager instance;

    private PoolManager() {}

    private void initialize()
    {
        Pools.set(BackgroundActor.class, SimplePool.backgroundActorPool);
        Pools.set(GrassActor.class, SimplePool.grassBackgroundActorPool);
        Pools.set(TreeManagerActor.class, SimplePool.treeBackgroundActorPool);

        Pools.set(ScoreViewActor.class, SimplePool.scoreActorPool);
    }

    public static PoolManager getInstance()
    {
        if (instance == null)
        {
            synchronized (PoolManager.class)
            {
                if (instance == null)
                {
                    instance = new PoolManager();
                    instance.initialize();
                }
            }
        }

        return instance;
    }

    public <T> T get(Class<T> type)
    {
        Pool<T> pool = Pools.get(type);
        if (pool == null)
            return null;

        return pool.obtain();
    }

    public void free(Pool.Poolable free)
    {
        if (free == null)
            return;

        Pools.free(free);
    }
}
