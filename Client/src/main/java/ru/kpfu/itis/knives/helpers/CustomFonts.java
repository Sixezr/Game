package ru.kpfu.itis.knives.helpers;

import javafx.scene.text.Font;

public enum CustomFonts {
    sarpanchNormal64(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.SARPANCH_REGUALR_PATH), 64)),
    sarpanchBold64(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.SARPANCH_BOLD_PATH), 64)),
    sarpanchBold30(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.SARPANCH_BOLD_PATH), 30)),

    robotoBold36(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.ROBOTO_BOLD_PATH), 36)),
    robotoNormal36(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 36)),
    robotoNormal30(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 30)),
    robotoNormal26(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 26)),
    robotoNormal24(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 24)),
    robotoNormal18(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 18)),
    robotoNormal15(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 15)),
    robotoNormal13(Font.loadFont(CustomFonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 13));

    public final Font font;

    CustomFonts(Font font) {
        this.font = font;
    }
}
