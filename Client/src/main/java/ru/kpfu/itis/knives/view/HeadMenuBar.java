package ru.kpfu.itis.knives.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.controllers.AboutController;
import ru.kpfu.itis.knives.controllers.AbstractController;
import ru.kpfu.itis.knives.controllers.RulesController;
import ru.kpfu.itis.knives.helpers.Colors;
import ru.kpfu.itis.knives.helpers.Fonts;

public final class HeadMenuBar extends MenuBar {
    // UI
    private final Label mainItemLabel;

    // Init
    public HeadMenuBar() {
        mainItemLabel = new Label("Главная");
        configureMenuBar();
    }

    // Create UI
    private void configureMenuBar() {
        setBackground(new Background(new BackgroundFill(Colors.lightBrown.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));

        mainItemLabel.setTextFill(Color.WHITE);
        mainItemLabel.setFont(Fonts.robotoNormal13.getFont());
        Menu mainItem = new Menu(null, mainItemLabel);

        Label rulesItemLabel = new Label("Правила");
        rulesItemLabel.setTextFill(Color.WHITE);
        rulesItemLabel.setFont(Fonts.robotoNormal13.getFont());
        Menu rulesItem = new Menu(null, rulesItemLabel);
        rulesItemLabel.setOnMouseClicked(event -> {
            Stage newWindow = new Stage();
            newWindow.setTitle("Правила");
            AbstractController controller = new RulesController(newWindow);
            controller.createScene();
        });

        Label aboutItemLabel = new Label("О нас");
        aboutItemLabel.setTextFill(Color.WHITE);
        aboutItemLabel.setFont(Fonts.robotoNormal13.getFont());
        Menu aboutItem = new Menu(null, aboutItemLabel);
        aboutItemLabel.setOnMouseClicked(event -> {
            Stage newWindow = new Stage();
            newWindow.setTitle("О нас");
            AbstractController controller = new AboutController(newWindow);
            controller.createScene();
        });

        getMenus().addAll(mainItem, rulesItem, aboutItem);
        setMinHeight(20);
    }

    // Getters
    public Label getMainItemLabel() {
        return mainItemLabel;
    }
}
