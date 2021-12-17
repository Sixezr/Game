package ru.kpfu.itis.knives.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import ru.kpfu.itis.knives.App;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.ProgressHBox;
import ru.kpfu.itis.knives.view.StartingVBox;

public class StartingScene extends Scene {
    // UI
    private final BorderPane mainPane;

    // Init
    public StartingScene(BorderPane pane) {
        super(pane, App.getMainStage().getWidth(), App.getMainStage().getHeight());
        mainPane = pane;
        mainPane.setTop(new HeadMenuBar());
        mainPane.setCenter(new StartingVBox());
        mainPane.setBottom(new ProgressHBox("Игра начинается"));
    }
}
