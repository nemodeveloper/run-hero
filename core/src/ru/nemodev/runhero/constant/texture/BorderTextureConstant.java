package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BorderTextureConstant
{
    public static final String GROUND_ATLAS = "atlas/border/ground/ground.atlas";
//    public static final String SKY_ATLAS = "atlas/border/sky/sky.atlas";

    public static final String GRASS_ATLAS = "atlas/border/ground/grass_ground.atlas";


    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            GROUND_ATLAS,
            GRASS_ATLAS
//            SKY_ATLAS
    ));

}
