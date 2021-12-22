package ru.kpfu.itis.knives.helpers;

import javafx.scene.paint.Color;

public enum Colors {
    beige(Color.web("#E7E5DB")),
    lightBrown(Color.web("#7E6D6D")),
    brown(Color.web("#A67B71")),
    green(Color.web("#79A67C")),
    blue(Color.web("#8DDFF2")),
    darkBlue(Color.web("#689AA6")),
    darkBrown(Color.web("#543D3D"));

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
