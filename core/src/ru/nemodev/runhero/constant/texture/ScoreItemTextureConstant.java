package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class ScoreItemTextureConstant
{
    private ScoreItemTextureConstant() { }

    public static final String SCORE_ITEM_ATLAS = "atlas/score_items/score_items.atlas";
    public static final String STAR_ITEM = "star_item";

    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            SCORE_ITEM_ATLAS
    ));

}
