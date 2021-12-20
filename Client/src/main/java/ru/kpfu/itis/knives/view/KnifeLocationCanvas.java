package ru.kpfu.itis.knives.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;


public class KnifeLocationCanvas extends Canvas {
    // Const
    private static final double WIDTH = 400;
    private static final double HEIGHT = 250;

    private static final double RATIO = 2.5;

    private static final String FILE_NAME = "/knife.png";

    // Properties
    private final GraphicsContext context;

    // Init
    public KnifeLocationCanvas() {
        super(WIDTH, HEIGHT);
        context = getGraphicsContext2D();

        drawBackground();
    }

    // Draw
    public void drawKnifeWithIncline(double incline) {
        try {
            Image image = new Image(getClass().getResourceAsStream(FILE_NAME));

            double x = WIDTH / 2 - image.getWidth();
            double y = HEIGHT * 0.75 - image.getHeight() + Math.abs(incline / RATIO);

            context.save();
            rotate(incline, x + image.getWidth() / 2, y + image.getHeight() / 2);

            context.drawImage(image, x, y);
            context.restore();
        } catch (Exception e) {
            // TODO: delete printStackTrace
            e.printStackTrace();
        }
    }

    // Private
    private void rotate(double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    private void drawBackground() {
        context.setFill(Color.BLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);

        context.setFill(Color.GREEN);
        context.fillRect(0, HEIGHT * 0.75, WIDTH, HEIGHT / 4);
    }
}
