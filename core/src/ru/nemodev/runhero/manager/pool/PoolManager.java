package ru.nemodev.runhero.manager.pool;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import ru.nemodev.runhero.entity.game.ScoreViewActor;
import ru.nemodev.runhero.entity.game.background.BackgroundActor;
import ru.nemodev.runhero.entity.game.background.GrassActor;
import ru.nemodev.runhero.entity.game.background.StarManagerActor;
import ru.nemodev.runhero.entity.game.background.TreeManagerActor;

/**
 * created by NemoDev on 08.05.2018 - 20:45
 */
public final class PoolManager
{
    private static volatile PoolManager instance = new PoolManager();

    private PoolManager()
    {
        initialize();
    }

    private void initialize()
    {
        Pools.set(BackgroundActor.class, SimplePool.backgroundActorPool);
        Pools.set(StarManagerActor.class, SimplePool.starManagerActorPool);
        Pools.set(TreeManagerActor.class, SimplePool.treeBackgroundActorPool);
        Pools.set(GrassActor.class, SimplePool.grassBackgroundActorPool);

        Pools.set(ScoreViewActor.class, SimplePool.scoreActorPool);
    }

    public static PoolManager getInstance()
    {
        return instance;
    }

    public <T extends Pool.Poolable> T get(Class<T> type)
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
