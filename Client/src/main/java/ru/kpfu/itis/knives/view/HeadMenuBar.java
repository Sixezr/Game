package ru.kpfu.itis.knives.view;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.kpfu.itis.knives.scenes.AboutScene;
import ru.kpfu.itis.knives.scenes.RulesScene;

public class HeadMenuBar extends MenuBar {
    // Init
    public HeadMenuBar() {
        configureMenuBar();
    }

    // Create UI
    private void configureMenuBar() {
        Label mainItemLabel = new Label("Главная");
        Menu mainItem = new Menu(null, mainItemLabel);

        Label rulesItemLabel = new Label("Правила");
        Menu rulesItem = new Menu(null, rulesItemLabel);
        rulesItemLabel.setOnMouseClicked(event -> {
            Stage newWindow = new Stage();
            newWindow.setTitle("Правила");
            newWindow.setScene(new RulesScene(new StackPane()));
            newWindow.show();
        });

        Label aboutItemLabel = new Label("О нас");
        Menu aboutItem = new Menu(null, aboutItemLabel);
        aboutItemLabel.setOnMouseClicked(event -> {
            Stage newWindow = new Stage();
            newWindow.setTitle("О нас");
            newWindow.setScene(new AboutScene(new StackPane()));
            newWindow.show();
        });

        getMenus().addAll(mainItem, rulesItem, aboutItem);
        setMinHeight(20);
    }
}
