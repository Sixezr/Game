package ru.kpfu.itis.knives.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Fonts;

public class ProgressHBox extends HBox {
    // UI
    private Label progressLabel;

    // Init
    public ProgressHBox(String status) {
        configureHBox(status);
    }

    // Create UI
    private void configureHBox(String status) {
        setBackground(new Background(new BackgroundFill(Colors.lightBrown.color, CornerRadii.EMPTY, Insets.EMPTY)));

        progressLabel = new Label(status);
        progressLabel.setTextFill(Color.WHITE);
        progressLabel.setFont(Fonts.robotoNormal13.font);
        getChildren().add(progressLabel);

        setPadding(new Insets(0, 0, 0, 20));
        setMinHeight(30);
        setHgrow(progressLabel, Priority.ALWAYS);
        setAlignment(Pos.CENTER_LEFT);
    }

    // Getters & Setters
    public String getStatus() {
        return progressLabel.getText();
    }

    public void setStatus(String status) {
        progressLabel.setText(status);
    }
}
