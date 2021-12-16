package ru.kpfu.itis.knives.view;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class HeadMenuBar extends MenuBar {
    // Init
    public HeadMenuBar() {
        configureMenuBar();
    }

    // Create UI
    private void configureMenuBar() {
        Label mainItemLabel = new Label("Главная");
        Menu mainItem = new Menu(null, mainItemLabel);
        mainItemLabel.setOnMouseClicked(event -> {
            // TODO: Add action for main
        });

        Label rulesItemLabel = new Label("Правила");
        Menu rulesItem = new Menu(null, rulesItemLabel);
        rulesItemLabel.setOnMouseClicked(event -> {
            // TODO: Add action for rules
        });

        Label aboutItemLabel = new Label("О нас");
        Menu aboutItem = new Menu(null, aboutItemLabel);
        aboutItemLabel.setOnMouseClicked(event -> {
            // TODO: Add action for about
        });

        getMenus().addAll(mainItem, rulesItem, aboutItem);
        setMinHeight(20);
    }
}
