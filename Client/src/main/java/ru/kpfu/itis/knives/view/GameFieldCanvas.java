package ru.kpfu.itis.knives.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class GameFieldCanvas extends Canvas {
    // Const
    private static final double DIAMETER = 500;

    // Properties
    private final GraphicsContext context;

    // Init
    public GameFieldCanvas() {
        super(DIAMETER, DIAMETER);
        context = getGraphicsContext2D();

        context.setFill(Color.AZURE);
        context.fillArc(0, 0, DIAMETER, DIAMETER, 90, 360 * 0.5, ArcType.ROUND);

        context.setFill(Color.DARKCYAN);
        context.fillArc(0, 0, DIAMETER, DIAMETER, 90, -360 * 0.5, ArcType.ROUND);

        context.setStroke(Color.BLACK);
        context.setLineWidth(4);
        context.strokeLine(250, 0, 250, 500);
    }

    public void drawNewRegions() {
        // TODO: big work...
    }
}
