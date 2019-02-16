package ru.nemodev.runhero.constant.texture;

import java.util.HashSet;
import java.util.Set;

public final class AtlasLoaderConstant
{
    private AtlasLoaderConstant() { }

    public static final Set<String> ATLAS_BODY_FOR_LOADING = buildAtlasForLoading();

    private static Set<String> buildAtlasForLoading()
    {
        Set<String> atlasForLoading = new HashSet<String>();

        atlasForLoading.addAll(BackgroundTextureConstant.ATLAS_FOR_LOAD);
        atlasForLoading.addAll(BorderTextureConstant.ATLAS_FOR_LOAD);
        atlasForLoading.addAll(HeroTextureConstant.ATLAS_FOR_LOAD);
        atlasForLoading.addAll(ScoreItemTextureConstant.ATLAS_FOR_LOAD);

        atlasForLoading.addAll(MobsStaticTextureConstant.ATLAS_FOR_LOAD);
        atlasForLoading.addAll(MobsAnimationTextureConstant.ATLAS_FOR_LOAD);

        return atlasForLoading;
    }

}
