package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class HeroTextureConstant
{
    private static final String BASE_HERO_ATLAS_PATH = "atlas/hero/";

    public static final String HERO_RUN_ATLAS = BASE_HERO_ATLAS_PATH + "hero_run.atlas";
    public static final String HERO_JUMP_ATLAS = BASE_HERO_ATLAS_PATH + "hero_jump.atlas";

    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            HERO_RUN_ATLAS,
            HERO_JUMP_ATLAS
    ));
}
