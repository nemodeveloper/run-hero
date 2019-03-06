package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class UITextureConstant
{
    private UITextureConstant() { }

    private static final String BASE_UI_PATH = "atlas/ui/";

    public static final String BUTTONS_UI_ATLAS = BASE_UI_PATH + "buttons.atlas";

    public static final String MENU_BUTTON_START = "button_play";
    public static final String MENU_BUTTON_EXIT = "button_close";
    public static final String MENU_BUTTON_SOUND_ON = "button_sound_on";
    public static final String MENU_BUTTON_SOUND_OFF = "button_sound_off";
    public static final String MENU_BUTTON_RATING = "button_star";

    public static final String GAME_PAUSE_BUTTON = "button_pause";
    public static final String GAME_PAUSE_POP_UP_BACKGROUND = "pop_up_frame";


    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            BUTTONS_UI_ATLAS
    ));

}
