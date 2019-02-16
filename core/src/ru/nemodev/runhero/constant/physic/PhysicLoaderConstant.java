package ru.nemodev.runhero.constant.physic;

import java.util.HashSet;
import java.util.Set;

public final class PhysicLoaderConstant
{
    private PhysicLoaderConstant() { }

    public static final Set<String> PHYSIC_BODY_FOR_LOADING = buildPhysicForLoading();

    private static Set<String> buildPhysicForLoading()
    {
        Set<String> physicForLoading = new HashSet<String>();
        physicForLoading.addAll(MobPhysicConstant.PHYSIC_FOR_LOADING);

        return physicForLoading;
    }

}