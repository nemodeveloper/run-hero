package ru.nemodev.runhero.core.manager.resource;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.core.physic.BodyEditorLoader;

public class PhysicBodyEditorLoader extends AsynchronousAssetLoader<BodyEditorLoader, PhysicBodyEditorLoader.PhysicLoaderParameter>
{
    private BodyEditorLoader bodyEditorLoader;

    public PhysicBodyEditorLoader(FileHandleResolver resolver)
    {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, PhysicLoaderParameter parameter)
    {
        bodyEditorLoader = new BodyEditorLoader(file);
    }

    @Override
    public BodyEditorLoader loadSync(AssetManager manager, String fileName, FileHandle file, PhysicLoaderParameter parameter)
    {
        BodyEditorLoader current = bodyEditorLoader;
        this.bodyEditorLoader = null;

        return current;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, PhysicLoaderParameter parameter)
    {
        return null;
    }

    public static class PhysicLoaderParameter extends AssetLoaderParameters<BodyEditorLoader>
    {

    }
}
