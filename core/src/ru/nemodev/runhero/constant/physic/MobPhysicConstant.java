package ru.nemodev.runhero.constant.physic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class MobPhysicConstant
{
    private MobPhysicConstant() { }

    private static final String MOB_BASE_PATH = "physic/mobs/";

    public static final String PHYSIC_MOB_STONE = MOB_BASE_PATH + "stone/mobs.json";
    public static final String LOADER_STONE_MOB_NAMES = "stone_mobs";
    public static final String[] STONE_MOB_NAMES = "stone_small,stone_medium,stone_big,stone_composite".split(",");

    public static Set<String> PHYSIC_FOR_LOADING = new HashSet<String>(Arrays.asList(
            PHYSIC_MOB_STONE
    ));

}
