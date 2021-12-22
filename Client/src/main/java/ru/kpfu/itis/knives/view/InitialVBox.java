package ru.kpfu.itis.knives.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.knives.App;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.CustomFonts;
import ru.kpfu.itis.knives.scenes.WaitingScene;

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
        mainNameLabel.setFont(CustomFonts.sarpanchBold64.font);

        Label descriptionLabel = new Label("Захватите территорию своего оппонента");
        descriptionLabel.setFont(CustomFonts.robotoBold36.font);

        Button startButton = new Button("Начать");
        startButton.setFont(CustomFonts.robotoNormal18.font);
        startButton.setOnAction(event -> {
            // TODO: add network action
            App.getMainStage().setScene(new WaitingScene(new BorderPane()));
        });

        getChildren().addAll(mainNameLabel, descriptionLabel, startButton);
    }
}
