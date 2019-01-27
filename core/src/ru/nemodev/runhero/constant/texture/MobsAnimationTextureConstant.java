package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class MobsAnimationTextureConstant
{
    public static final String MOB_CUBE_AROUND_1_ATLAS = "atlas/mobs/animation/cube/around/1.atlas";
    public static final String MOB_CUBE_AROUND_2_ATLAS = "atlas/mobs/animation/cube/around/2.atlas";
    public static final String MOB_CUBE_AROUND_3_ATLAS = "atlas/mobs/animation/cube/around/3.atlas";

    public static final String MOB_CUBE_MIMIC_1_ATLAS = "atlas/mobs/animation/cube/mimic/1.atlas";
    public static final String MOB_CUBE_MIMIC_2_ATLAS = "atlas/mobs/animation/cube/mimic/2.atlas";
    public static final String MOB_CUBE_MIMIC_3_ATLAS = "atlas/mobs/animation/cube/mimic/3.atlas";
    public static final String MOB_CUBE_MIMIC_4_ATLAS = "atlas/mobs/animation/cube/mimic/4.atlas";

    public static final String MOB_CUBE_YEAS_1_ATLAS = "atlas/mobs/animation/cube/yeas/1.atlas";
    public static final String MOB_CUBE_YEAS_2_ATLAS = "atlas/mobs/animation/cube/yeas/2.atlas";
    public static final String MOB_CUBE_YEAS_3_ATLAS = "atlas/mobs/animation/cube/yeas/3.atlas";
    public static final String MOB_CUBE_YEAS_4_ATLAS = "atlas/mobs/animation/cube/yeas/4.atlas";


    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            MOB_CUBE_AROUND_1_ATLAS,
            MOB_CUBE_AROUND_2_ATLAS,
            MOB_CUBE_AROUND_3_ATLAS,

            MOB_CUBE_MIMIC_1_ATLAS,
            MOB_CUBE_MIMIC_2_ATLAS,
            MOB_CUBE_MIMIC_3_ATLAS,
            MOB_CUBE_MIMIC_4_ATLAS,

            MOB_CUBE_YEAS_1_ATLAS,
            MOB_CUBE_YEAS_2_ATLAS,
            MOB_CUBE_YEAS_3_ATLAS,
            MOB_CUBE_YEAS_4_ATLAS
    ));

}
