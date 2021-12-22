package ru.kpfu.itis.knives.view;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.controllers.AboutController;
import ru.kpfu.itis.knives.controllers.AbstractController;
import ru.kpfu.itis.knives.controllers.RulesController;
import ru.kpfu.itis.knives.helpers.CustomFonts;

public class HeadMenuBar extends MenuBar {
    // Init
    public HeadMenuBar() {
        configureMenuBar();
    }

    // Create UI
    private void configureMenuBar() {
        Label mainItemLabel = new Label("Главная");
        mainItemLabel.setFont(CustomFonts.robotoNormal13.font);
        Menu mainItem = new Menu(null, mainItemLabel);

        Label rulesItemLabel = new Label("Правила");
        rulesItemLabel.setFont(CustomFonts.robotoNormal13.font);
        Menu rulesItem = new Menu(null, rulesItemLabel);
        rulesItemLabel.setOnMouseClicked(event -> {
            Stage newWindow = new Stage();
            newWindow.setTitle("Правила");
            AbstractController controller = new RulesController(newWindow);
            controller.createScene();
        });

        Label aboutItemLabel = new Label("О нас");
        aboutItemLabel.setFont(CustomFonts.robotoNormal13.font);
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
