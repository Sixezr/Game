package ru.kpfu.itis.knives.view;

import javafx.animation.FadeTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Fonts;
import ru.kpfu.itis.knives.helpers.KnifeState;

import java.util.Timer;
import java.util.TimerTask;


public final class KnifeLocationCanvas extends Canvas {
    // Const
    private static final double WIDTH = 400;
    private static final double HEIGHT = 250;

    private static final double RATIO = 2.45;

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

    /**
     * @param incline incline of knife
     * @param state   success or failure
     * @apiNote Run in another thread!
     */
    public void drawKnifeWithIncline(double incline, KnifeState state) {
        context.clearRect(0, 0, getWidth(), getHeight());

        FadeTransition animation = new FadeTransition(Duration.seconds(1), this);
        animation.setFromValue(0);
        animation.setToValue(1);
        animation.setCycleCount(1);
        animation.play();

        drawBackground();

        Image image = new Image(getClass().getResourceAsStream(FILE_NAME));

        double endX = WIDTH / 2 - image.getWidth();
        double endY = HEIGHT * 0.75 - image.getHeight() + Math.abs((90 - incline) / RATIO);

        context.save();
        rotate(90 - incline, endX + image.getWidth() / 2, endY + image.getHeight() / 2);

        context.drawImage(image, endX, endY);

        context.restore();

        context.setFill(Colors.darkBlue.getColor());
        context.setFont(Fonts.robotoNormal30.getFont());

        Text text = new Text(state.label);
        text.setFont(Fonts.robotoNormal30.getFont());
        double wordWidth = text.getLayoutBounds().getWidth();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                context.fillText(state.label, WIDTH / 2 - wordWidth / 2, 30);
            }
        }, 1000);
    }

    // Private
    private void rotate(double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    private void drawBackground() {
        context.setFill(Colors.blue.getColor());
        context.fillRect(0, 0, WIDTH, HEIGHT);

        context.setFill(Colors.green.getColor());
        context.fillRect(0, HEIGHT * 0.75, WIDTH, HEIGHT / 4);
    }
}
