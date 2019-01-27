package ru.nemodev.runhero.entity.play.score_item.strategy.spawn;

import com.badlogic.gdx.math.Vector3;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import ru.nemodev.runhero.entity.play.score_item.ScoreItemActor;

public interface ScoreItemSpawnStrategy
{
    boolean isCanSpawn(int playerScore);

    ScoreItemActor<? extends Box2DSprite> spawn(float timeDelta, Vector3 playerPosition);

}
