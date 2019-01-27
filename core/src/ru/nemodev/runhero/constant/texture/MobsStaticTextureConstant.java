package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class MobsStaticTextureConstant
{
    public static final String MOB_CUBE_1_ATLAS = "atlas/mobs/static/cube/cube.atlas";

    public static final String[] MOB_CUBE_KEYS = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15".split(" ");

    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            MOB_CUBE_1_ATLAS
    ));

}
