package ru.kpfu.itis.knives.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.CustomFonts;

public class InitialVBox extends VBox {
    // UI
    private final Button startButton;

    // Init
    public InitialVBox() {
        startButton = new Button("Начать");
        configureVBox();
    }

    // Create UI
    private void configureVBox() {
        setSpacing(50);
        setAlignment(Pos.CENTER);

        Label mainNameLabel = new Label(Constants.MAIN_NAME);
        mainNameLabel.setFont(CustomFonts.sarpanchBold64.font);

        Label descriptionLabel = new Label("Захватите территорию своего оппонента");
        descriptionLabel.setFont(CustomFonts.robotoBold36.font);

        startButton.setFont(CustomFonts.robotoNormal18.font);

        getChildren().addAll(mainNameLabel, descriptionLabel, startButton);
    }

    // Getters
    public Button getStartButton() {
        return startButton;
    }
}
