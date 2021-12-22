package ru.kpfu.itis.knives.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.Fonts;

public class WaitingVBox extends VBox {
    // UI
    private final Button cancelButton;

    // Init
    public WaitingVBox() {
        cancelButton = new Button("Отменить");
        configureVBox();
    }

    // Create UI
    private void configureVBox() {
        setBackground(new Background(new BackgroundFill(Colors.beige.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        setSpacing(50);
        setAlignment(Pos.CENTER);

        Label mainNameLabel = new Label(Constants.MAIN_NAME);
        mainNameLabel.setFont(Fonts.sarpanchBold64.getFont());

        Label descriptionLabel = new Label("Ожидание соперника...");
        descriptionLabel.setFont(Fonts.robotoBold36.getFont());

        cancelButton.setFont(Fonts.robotoNormal18.getFont());
        cancelButton.setTextFill(Color.WHITE);
        cancelButton.setBackground(new Background(new BackgroundFill(Colors.darkBrown.getColor(), new CornerRadii(15), Insets.EMPTY)));


        getChildren().addAll(mainNameLabel, descriptionLabel, cancelButton);
    }

    // Getters

    public Button getCancelButton() {
        return cancelButton;
    }
}
