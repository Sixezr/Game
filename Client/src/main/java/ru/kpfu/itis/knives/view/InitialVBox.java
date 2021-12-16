package ru.kpfu.itis.knives.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ru.kpfu.itis.knives.helpers.Constants;

public class InitialVBox extends VBox {
    // Init
    public InitialVBox() {
        configureVBox();
    }

    // Create UI
    private void configureVBox() {
        setSpacing(50);
        setAlignment(Pos.CENTER);

        Label mainNameLabel = new Label(Constants.MAIN_NAME);
        mainNameLabel.setFont(Font.font(64));

        Label descriptionLabel = new Label("Захватите территорию своего оппонента");
        descriptionLabel.setFont(Font.font(36));

        Button startButton = new Button("Начать");
        startButton.setFont(Font.font(18));
        startButton.setOnAction(event -> {
            // TODO: add some action
        });

        getChildren().addAll(mainNameLabel, descriptionLabel, startButton);
    }
}
