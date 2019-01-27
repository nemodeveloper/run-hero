package ru.nemodev.runhero.scene.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.scene.collision.SimpleContactListener;

public abstract class Box2dScene extends BaseScene
{
    private static final float TIME_STEP = 1.f / 60.f;
    private static final float MAX_WORLD_STEP = 0.25f;

    private static final int VELOCITY_ITERATION = 6;
    private static final int POSITION_ITERATION = 2;

    private float accumulator;
    protected final World world;

    private Box2DDebugRenderer debugRenderer;
    private boolean drawDebug;

    public Box2dScene(World world, Viewport viewport, Batch batch)
    {
        super(viewport, batch);

        this.accumulator = 0.f;
        this.world = world;

    }

    @Override
    public void init()
    {
        super.init();

        this.world.setContactListener(getContactListener());
        this.debugRenderer = new Box2DDebugRenderer();
        this.drawDebug = false;
    }

    protected ContactListener getContactListener()
    {
        return new SimpleContactListener();
    }

    @Override
    protected void doUpdate(float delta)
    {
        if (isNeedPhysicStep())
        {
            doPhysicsStep(delta);
        }

        super.doUpdate(delta);
    }

    @Override
    protected void doDraw()
    {
        super.doDraw();

        if (drawDebug)
        {
            debugRenderer.render(world, getCamera().combined);
        }
    }

    protected boolean isNeedPhysicStep()
    {
        return true;
    }

    private void doPhysicsStep(float deltaTime)
    {
        accumulator += Math.min(deltaTime, MAX_WORLD_STEP);
        while (accumulator >= TIME_STEP)
        {
            world.step(TIME_STEP, VELOCITY_ITERATION, POSITION_ITERATION);
            accumulator -= TIME_STEP;
        }
    }

    @Override
    public void dispose()
    {
        super.dispose();
        world.dispose();
    }

}
