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

public class HeadMenuBar extends MenuBar {
    // Init
    public HeadMenuBar() {
        configureMenuBar();
    }

    // Create UI
    private void configureMenuBar() {
        setBackground(new Background(new BackgroundFill(Colors.lightBrown.color, CornerRadii.EMPTY, Insets.EMPTY)));

        Label mainItemLabel = new Label("Главная");
        mainItemLabel.setTextFill(Color.WHITE);
        mainItemLabel.setFont(Fonts.robotoNormal13.font);
        Menu mainItem = new Menu(null, mainItemLabel);

        Label rulesItemLabel = new Label("Правила");
        rulesItemLabel.setTextFill(Color.WHITE);
        rulesItemLabel.setFont(Fonts.robotoNormal13.font);
        Menu rulesItem = new Menu(null, rulesItemLabel);
        rulesItemLabel.setOnMouseClicked(event -> {
            Stage newWindow = new Stage();
            newWindow.setTitle("Правила");
            AbstractController controller = new RulesController(newWindow);
            controller.createScene();
        });

        Label aboutItemLabel = new Label("О нас");
        aboutItemLabel.setTextFill(Color.WHITE);
        aboutItemLabel.setFont(Fonts.robotoNormal13.font);
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
}
