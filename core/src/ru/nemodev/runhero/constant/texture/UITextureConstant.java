package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class UITextureConstant
{
    private UITextureConstant() { }

    private static final String BASE_UI_PATH = "atlas/interface/";

    public static final String COMMON_UI_ATLAS = BASE_UI_PATH + "common_ui.atlas";

    public static final String BUTTON_START = "button_start";
    public static final String BUTTON_START_TOUCHED = "button_start_touched";

    public static final String BUTTON_EXIT = "button_exit";
    public static final String BUTTON_EXIT_TOUCHED = "button_exit_touched";

    public static final String BUTTON_CLOSE = "button_close";
    public static final String BUTTON_CLOSE_TOUCHED = "button_close_touched";

    public static final String BUTTON_SOUND_ON = "button_sound_on";
    public static final String BUTTON_SOUND_ON_TOUCHED = "button_sound_on_touched";

    public static final String BUTTON_SOUND_OFF = "button_sound_off";
    public static final String BUTTON_SOUND_OFF_TOUCHED = "button_sound_off_touched";

    public static final String BUTTON_RATING = "button_rating";
    public static final String BUTTON_RATING_TOUCHED = "button_rating_touched";

    public static final String BUTTON_MENU = "button_menu";
    public static final String BUTTON_MENU_TOUCHED = "button_menu_touched";

    public static final String BUTTON_PAUSE = "button_pause";
    public static final String BUTTON_PAUSE_TOUCHED = "button_pause_touched";

    public static final String GAME_STATUS_BOARD = "game_status_board";


    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            COMMON_UI_ATLAS
    ));

}
