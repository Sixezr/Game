package ru.kpfu.itis.knives.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import ru.kpfu.itis.knives.helpers.Closure;

import java.util.Optional;

public final class AlertController {
    /**
     * @param closure the block of code which start when user want to exit
     */
    public void createExitAlert(Closure closure) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтвердите");
        alert.setHeaderText(null);
        alert.setContentText("Вы хотите выйти?");

        Optional<ButtonType> button = alert.showAndWait();
        if (button.isPresent()) {
            if (button.get() == ButtonType.OK) {
                closure.execute();
            }
        }
    }

    /**
     *
     * @param title title of alert
     * @param message message in alert
     */
    public void createErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
