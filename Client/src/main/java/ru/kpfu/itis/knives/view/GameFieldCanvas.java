package ru.kpfu.itis.knives.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.kpfu.itis.knives.entities.Region;

public class GameFieldCanvas extends Canvas {
    // Const
    private static final double DIAMETER = 500;
    private static final double RATIO = 2.5;

    // Properties
    private final GraphicsContext context;

    // Init
    public GameFieldCanvas() {
        super(DIAMETER, DIAMETER);
        context = getGraphicsContext2D();

        drawCircle();

        context.setStroke(Color.BLACK);
        context.setLineWidth(1);
        context.strokeLine(250, 0, 250, 500);
    }

    // Redraw
    public void drawNewRegions(Region region) {
        context.clearRect(0, 0, DIAMETER, DIAMETER);

        drawCircle();

        // TODO: draw region bound
    }

    // Private
    private double convertPointCoordinate(double point) {
        if (point == 0) {
            return DIAMETER / 2;
        } else {
            return (DIAMETER / 2) + point * RATIO;
        }
    }

    private void drawCircle() {
        context.setFill(Color.AZURE);
        context.fillOval(0, 0, DIAMETER, DIAMETER);

        int STROKES = 1000;
        double angleIncr = 360.0 / STROKES;

        context.setLineWidth(1);
        double prevX = DIAMETER / 2;
        double prevY = 0;
        double angle = angleIncr;
        for (int i = 0; i < STROKES; i++, angle += angleIncr) {
            double radians = Math.toRadians(angle);
            int x = (int) (Math.cos(radians) * DIAMETER / 2);
            int y = (int) (Math.sin(radians) * DIAMETER / 2);
            context.strokeLine(250 + prevX, 250 + prevY, 250 + x, 250 + y);
            prevX = x;
            prevY = y;
        }
    }
}
