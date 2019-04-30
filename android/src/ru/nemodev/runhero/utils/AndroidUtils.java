package ru.nemodev.runhero.utils;


import android.content.Context;

public final class AndroidUtils
{
    private AndroidUtils() { }

    public static String getString(Context context, int resId)
    {
        return context.getResources().getString(resId);
    }
}
