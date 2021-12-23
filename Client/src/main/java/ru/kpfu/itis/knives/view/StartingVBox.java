package ru.kpfu.itis.knives.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Constants;
import ru.kpfu.itis.knives.helpers.Fonts;

public final class StartingVBox extends VBox {
    // Properties
    private final boolean isCurrentPlayerFirst;

    // Init
    public StartingVBox(boolean isCurrentPlayerFirst) {
        this.isCurrentPlayerFirst = isCurrentPlayerFirst;
        configureVBox();
    }

    // Create UI
    private void configureVBox() {
        setBackground(new Background(new BackgroundFill(Colors.beige.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        setSpacing(50);
        setAlignment(Pos.CENTER);

        Label mainNameLabel = new Label(Constants.MAIN_NAME);
        mainNameLabel.setFont(Fonts.sarpanchBold64.getFont());

        Label descriptionLabel = new Label("Игра начинается");
        descriptionLabel.setFont(Fonts.robotoBold36.getFont());

        HBox playersHBox = new HBox();
        playersHBox.setAlignment(Pos.CENTER);
        playersHBox.setSpacing(200);

        Label currentPlayerLabel = new Label("Вы");
        currentPlayerLabel.setMinWidth(150);
        currentPlayerLabel.setPadding(new Insets(5, 55, 5, 55));
        currentPlayerLabel.setFont(Fonts.robotoNormal26.getFont());
        currentPlayerLabel.setTextFill(Color.WHITE);
        currentPlayerLabel.setBackground(new Background(new BackgroundFill(Colors.darkBrown.getColor(), new CornerRadii(15), Insets.EMPTY)));

        Label enemyPlayerLabel = new Label("Соперник");
        enemyPlayerLabel.setMinWidth(150);
        enemyPlayerLabel.setPadding(new Insets(5, 20, 5, 20));
        enemyPlayerLabel.setFont(Fonts.robotoNormal26.getFont());
        enemyPlayerLabel.setTextFill(Color.WHITE);
        enemyPlayerLabel.setBackground(new Background(new BackgroundFill(Colors.darkBrown.getColor(), new CornerRadii(15), Insets.EMPTY)));

        Label winnerLabel = isCurrentPlayerFirst ? currentPlayerLabel : enemyPlayerLabel;
        winnerLabel.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(4))));

        playersHBox.getChildren().addAll(currentPlayerLabel, enemyPlayerLabel);

        getChildren().addAll(mainNameLabel, descriptionLabel, playersHBox);
    }
}
