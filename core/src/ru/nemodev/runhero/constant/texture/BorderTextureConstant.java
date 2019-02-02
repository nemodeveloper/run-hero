package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BorderTextureConstant
{
    private static final String BASE_BORDER_PATH = "atlas/border/ground/";

    public static final String GROUND_ATLAS = BASE_BORDER_PATH + "ground.atlas";
//    public static final String SKY_ATLAS = "atlas/border/sky/sky.atlas";

    public static final String GRASS_ATLAS = BASE_BORDER_PATH + "grass_ground.atlas";
    public static final String GRASS_GROUND = "grass_ground";


    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            GROUND_ATLAS,
            GRASS_ATLAS
//            SKY_ATLAS
    ));

}
