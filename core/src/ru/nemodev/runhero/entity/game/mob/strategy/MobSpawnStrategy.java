package ru.nemodev.runhero.entity.game.mob.strategy;

import com.badlogic.gdx.math.Vector3;

import ru.nemodev.runhero.entity.game.mob.kind.BaseMobActor;

public interface MobSpawnStrategy
{
    BaseMobActor spawn(Vector3 cameraPos);

    boolean isCanSpawn(int condition);
}
