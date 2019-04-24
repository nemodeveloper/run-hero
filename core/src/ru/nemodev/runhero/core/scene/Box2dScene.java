package ru.nemodev.runhero.core.scene;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.core.physic.collision.SimpleContactListener;

public abstract class Box2dScene extends BaseScene
{
    private static final float TIME_STEP = 1.f / 60.f;
    private static final float MAX_STEP = 0.25f;

    private static final int VELOCITY_ITERATION = 6;
    private static final int POSITION_ITERATION = 2;

    protected final World world;
    private float accumulator;

    private Box2DDebugRenderer debugRenderer;
    private boolean drawDebug;

    public Box2dScene(World world, Viewport viewport, Batch batch)
    {
        super(viewport, batch);
        this.world = world;
        this.accumulator = 0.f;

        init();
    }

    private void init()
    {
        this.world.setContactListener(getContactListener());
        this.debugRenderer = new Box2DDebugRenderer();
        this.drawDebug = false;
    }

    protected ContactListener getContactListener()
    {
        return new SimpleContactListener();
    }

    @Override
    protected void update(float delta)
    {
        if (isNeedUpdate())
        {
//            accumulator += Math.min(delta, MAX_STEP);
//            while (accumulator >= TIME_STEP)
//            {
//                world.step(TIME_STEP, VELOCITY_ITERATION, POSITION_ITERATION);
//                super.update(TIME_STEP);
//
//                accumulator -= TIME_STEP;
//                if (accumulator < 0.f)
//                {
//                    accumulator = 0.f;
//                }
//            }
            world.step(delta, VELOCITY_ITERATION, POSITION_ITERATION);
            super.update(delta);
        }
    }

    @Override
    protected void draw()
    {
        super.draw();

        if (drawDebug)
        {
            debugRenderer.render(world, getCamera().combined);
        }
    }

    @Override
    public void dispose()
    {
        super.dispose();
        world.dispose();
    }

}
