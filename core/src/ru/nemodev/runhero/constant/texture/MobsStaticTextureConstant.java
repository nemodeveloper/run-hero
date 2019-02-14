package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class MobsStaticTextureConstant
{
    public static final String BASE_MOB_PATH = "atlas/mobs/static/";

    public static final String STONE_SMALL_MOB_ATLAS = BASE_MOB_PATH + "stone/stone_small.atlas";
    public static final String STONE_MEDIUM_MOB_ATLAS = BASE_MOB_PATH + "stone/stone_medium.atlas";
    public static final String STONE_BIG_MOB_ATLAS = BASE_MOB_PATH + "stone/stone_big.atlas";
    public static final String STONE_COMPOSITE_MOB_ATLAS = BASE_MOB_PATH + "stone/stone_composite.atlas";

    public static final String[] STONE_MOB_NAMES = "1 2 3 4 5".split(" ");

    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            STONE_SMALL_MOB_ATLAS,
            STONE_MEDIUM_MOB_ATLAS,
            STONE_BIG_MOB_ATLAS,
            STONE_COMPOSITE_MOB_ATLAS
    ));

}
