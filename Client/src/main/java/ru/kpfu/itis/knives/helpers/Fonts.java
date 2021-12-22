package ru.kpfu.itis.knives.helpers;

import javafx.scene.text.Font;

public enum Fonts {
    sarpanchNormal64(Font.loadFont(Fonts.class.getResourceAsStream(Constants.SARPANCH_REGUALR_PATH), 64)),
    sarpanchBold64(Font.loadFont(Fonts.class.getResourceAsStream(Constants.SARPANCH_BOLD_PATH), 64)),
    sarpanchBold30(Font.loadFont(Fonts.class.getResourceAsStream(Constants.SARPANCH_BOLD_PATH), 30)),

    robotoBold36(Font.loadFont(Fonts.class.getResourceAsStream(Constants.ROBOTO_BOLD_PATH), 36)),
    robotoNormal36(Font.loadFont(Fonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 36)),
    robotoNormal30(Font.loadFont(Fonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 30)),
    robotoNormal26(Font.loadFont(Fonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 26)),
    robotoNormal24(Font.loadFont(Fonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 24)),
    robotoNormal18(Font.loadFont(Fonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 18)),
    robotoNormal15(Font.loadFont(Fonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 15)),
    robotoNormal13(Font.loadFont(Fonts.class.getResourceAsStream(Constants.ROBOTO_NORMAL_PATH), 13));

    private final Font font;

    Fonts(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}
