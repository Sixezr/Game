package ru.kpfu.itis.knives.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import ru.kpfu.itis.knives.helpers.CustomFonts;

public class ProgressHBox extends HBox {
    // UI
    private Label progressLabel;

    // Init
    public ProgressHBox(String status) {
        configureHBox(status);
    }

    // Create UI
    private void configureHBox(String status) {
        progressLabel = new Label(status);
        progressLabel.setFont(CustomFonts.robotoNormal13.font);
        getChildren().add(progressLabel);

        setPadding(new Insets(0, 0, 0, 20));
        setMinHeight(30);
        setHgrow(progressLabel, Priority.ALWAYS);
        setAlignment(Pos.CENTER_LEFT);
        setStyle("-fx-background-color: grey");
    }

    // Getters & Setters
    public String getStatus() {
        return progressLabel.getText();
    }

    public void setStatus(String status) {
        progressLabel.setText(status);
    }
}
