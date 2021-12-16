package ru.kpfu.itis.knives.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import ru.kpfu.itis.knives.App;
import ru.kpfu.itis.knives.view.HeadMenuBar;
import ru.kpfu.itis.knives.view.InitialVBox;
import ru.kpfu.itis.knives.view.ProgressHBox;

public final class InitialScene extends Scene {
    // UI
    private static final BorderPane mainPane = new BorderPane();

    // Init
    public InitialScene() {
        super(mainPane, App.getMainStage().getWidth(), App.getMainStage().getHeight());
        mainPane.setTop(new HeadMenuBar());
        mainPane.setCenter(new InitialVBox());
        mainPane.setBottom(new ProgressHBox("Начните игру"));
    }
}
