package ru.nemodev.runhero.core.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public final class FileUtils
{
    private FileUtils() { }

    public static FileHandle getInternalFile(String fileName)
    {
        return Gdx.files.internal(fileName);
    }

}
