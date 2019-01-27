package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class HeroTextureConstant
{
    public static final String JELE_GREEN_ATLAS = "atlas/hero/jele1.atlas";
    public static final String JELE_YELLOW_ATLAS = "atlas/hero/jele2.atlas";

    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            JELE_GREEN_ATLAS,
            JELE_YELLOW_ATLAS
    ));
}
