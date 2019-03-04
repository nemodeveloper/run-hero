package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class BackgroundTextureConstant
{
    private BackgroundTextureConstant() {}

    public static final String SPLASH = "splash.png";

    private static final String BASE_BACKGROUND_PATH = "atlas/background/";

    public static final String BACKGROUND_ATLAS = BASE_BACKGROUND_PATH + "background.atlas";
    public static final String BACKGROUND = "background";

    public static final String STARS_BACKGROUND_ATLAS = BASE_BACKGROUND_PATH + "stars_background.atlas";
    public static final String STARS = "stars";

    public static final String TREE_BACKGROUND_ATLAS = BASE_BACKGROUND_PATH + "tree_background.atlas";
    public static final String TREE_1 = "1";
    public static final String TREE_2 = "2";
    public static final String TREE_3 = "3";
    public static final String TREE_4 = "4";

    public static final String MOON_BACKGROUND_ATLAS = BASE_BACKGROUND_PATH + "moon_background.atlas";
    public static final String MOON = "moon";

    public static final String GRASS_BACKGROUND_ATLAS = BASE_BACKGROUND_PATH + "grass_background.atlas";
    public static final String GRASS_BACKGROUND = "grass_background";

    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            BACKGROUND_ATLAS,
            STARS_BACKGROUND_ATLAS,
            TREE_BACKGROUND_ATLAS,
            MOON_BACKGROUND_ATLAS,
            GRASS_BACKGROUND_ATLAS
    ));

}
