package ru.nemodev.runhero.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class SoundConstant
{
    private SoundConstant() { }

    private static final String BASE_SOUND_PATH = "sound/";

    // music
    public static final String MAIN_THEME_MUSIC = BASE_SOUND_PATH + "main_theme.mp3";


    public static Set<String> MUSIC_FOR_LOADING = new HashSet<String>(Arrays.asList(
            MAIN_THEME_MUSIC
    ));

    // sound
    public static final String HERO_JUMP_SOUND = BASE_SOUND_PATH + "hero_jump.wav";


    public static Set<String> SOUND_FOR_LOADING = new HashSet<String>(Arrays.asList(
            HERO_JUMP_SOUND
    ));

}
