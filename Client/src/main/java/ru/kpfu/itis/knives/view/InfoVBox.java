package ru.kpfu.itis.knives.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ru.kpfu.itis.knives.App;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.scenes.InitialScene;

public class InfoVBox extends VBox {
    // Init
    public InfoVBox() {
        configureVBox();
    }

    // Create UI
    private void configureVBox() {
        setSpacing(50);
        setAlignment(Pos.CENTER);

        Label mainNameLabel = new Label(Constants.MAIN_NAME);
        mainNameLabel.setFont(Font.font(64));

        Label descriptionLabel = new Label("Ожидание соперника...");
        descriptionLabel.setFont(Font.font(36));

        Button startButton = new Button("Отменить");
        startButton.setFont(Font.font(18));
        startButton.setOnAction(event -> {
            // TODO: add network action
            App.getMainStage().setScene(new InitialScene(new BorderPane()));
        });

        getChildren().addAll(mainNameLabel, descriptionLabel, startButton);
    }
}
