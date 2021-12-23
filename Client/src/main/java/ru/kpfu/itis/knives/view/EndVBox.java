package ru.kpfu.itis.knives.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.Fonts;

public class EndVBox extends VBox {
    // UI
    private final Button okButton;

    // Properties
    private final boolean isCurrentPlayerWon;

    // Init
    public EndVBox(boolean isCurrentPlayerWon) {
        okButton = new Button("Окей");
        this.isCurrentPlayerWon = isCurrentPlayerWon;

        configureVBox();
    }

    // Create UI
    private void configureVBox() {
        setBackground(new Background(new BackgroundFill(Colors.beige.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        setSpacing(50);
        setAlignment(Pos.CENTER);

        Label mainNameLabel = new Label(Constants.MAIN_NAME);
        mainNameLabel.setFont(Fonts.sarpanchBold64.getFont());

        Label descriptionLabel = new Label("Результат:");
        descriptionLabel.setFont(Fonts.robotoBold36.getFont());

        HBox playersHBox = new HBox();
        playersHBox.setAlignment(Pos.CENTER);
        playersHBox.setSpacing(200);

        Label enemyPlayerLabel = new Label("Соперник");
        enemyPlayerLabel.setMinWidth(150);
        enemyPlayerLabel.setPadding(new Insets(5, 20, 5, 20));
        enemyPlayerLabel.setFont(Fonts.robotoNormal26.getFont());
        enemyPlayerLabel.setTextFill(Color.WHITE);
        enemyPlayerLabel.setBackground(new Background(new BackgroundFill(Colors.darkBrown.getColor(), new CornerRadii(15), Insets.EMPTY)));

        Label currentPlayerLabel = new Label("Вы");
        currentPlayerLabel.setMinWidth(150);
        currentPlayerLabel.setPadding(new Insets(5, 55, 5, 55));
        currentPlayerLabel.setFont(Fonts.robotoNormal26.getFont());
        currentPlayerLabel.setTextFill(Color.WHITE);
        currentPlayerLabel.setBackground(new Background(new BackgroundFill(Colors.darkBrown.getColor(), new CornerRadii(15), Insets.EMPTY)));

        Label winnerLabel = isCurrentPlayerWon ? currentPlayerLabel : enemyPlayerLabel;
        winnerLabel.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(4))));

        playersHBox.getChildren().addAll(currentPlayerLabel, enemyPlayerLabel);

        okButton.setPadding(new Insets(5, 20, 5, 20));
        okButton.setFont(Fonts.robotoNormal18.getFont());
        okButton.setTextFill(Color.WHITE);
        okButton.setBackground(new Background(new BackgroundFill(Colors.darkBrown.getColor(), new CornerRadii(15), Insets.EMPTY)));

        getChildren().addAll(mainNameLabel, descriptionLabel, playersHBox, okButton);
    }

    // Getters
    public Button getOkButton() {
        return okButton;
    }
}
