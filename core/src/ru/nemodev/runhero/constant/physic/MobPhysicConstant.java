package ru.nemodev.runhero.constant.physic;

public final class MobPhysicConstant
{
    private MobPhysicConstant() { }

    private static final String MOB_BASE_PATH = "physic/mobs/";

    public static final String MOB_STONE_PATH = MOB_BASE_PATH + "stone/mobs.json";
    public static final String[] STONE_MOBS = "stone_small,stone_medium,stone_big".split(",");

}
