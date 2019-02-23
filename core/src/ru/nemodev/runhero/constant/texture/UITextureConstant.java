package ru.nemodev.runhero.constant.texture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class UITextureConstant
{
    private UITextureConstant() { }

    private static final String BASE_UI_PATH = "atlas/ui/";

    public static final String BUTTONS_UI_ATLAS = BASE_UI_PATH + "buttons.atlas";

    public static final String MENU_START_BUTTON = "menu_play_button";
    public static final String MENU_EXIT_BUTTON = "menu_exit_button";

    public static final String GAME_PAUSE_BUTTON = "game_pause_button";


    public static final Set<String> ATLAS_FOR_LOAD = new HashSet<String>(Arrays.asList(
            BUTTONS_UI_ATLAS
    ));

}
