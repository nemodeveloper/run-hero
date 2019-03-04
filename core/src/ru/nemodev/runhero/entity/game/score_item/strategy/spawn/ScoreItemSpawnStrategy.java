package ru.nemodev.runhero.entity.game.score_item.strategy.spawn;

import com.badlogic.gdx.math.Vector3;

import ru.nemodev.runhero.entity.game.score_item.ScoreItemActor;

public interface ScoreItemSpawnStrategy
{
    boolean isCanSpawn(int playerScore);

    ScoreItemActor spawn(float timeDelta, Vector3 playerPosition);

}
