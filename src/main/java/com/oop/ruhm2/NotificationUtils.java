package com.oop.ruhm2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NotificationUtils {

    public static void displayNotification(String message, Color backgroundColor, Color textColor, Stage stage) {
        Label notificationLabel = new Label(message);
        notificationLabel.setStyle("-fx-background-color: rgba(" +
                (int)(backgroundColor.getRed() * 255) + "," +
                (int)(backgroundColor.getGreen() * 255) + "," +
                (int)(backgroundColor.getBlue() * 255) + "," +
                "0.8); -fx-text-fill: " + textColor.toString().replace("0x", "#") + "; -fx-font-size: 16px;");
        notificationLabel.setMinWidth(200);
        notificationLabel.setMaxWidth(400);
        notificationLabel.setWrapText(true);

        StackPane notificationPane = new StackPane(notificationLabel);
        notificationPane.setStyle("-fx-background-color: transparent;");

        Scene notificationScene = new Scene(notificationPane, Color.TRANSPARENT);

        stage.setScene(notificationScene);

        Timeline fadeInTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(notificationPane.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(notificationPane.opacityProperty(), 1.0))
        );

        Timeline fadeOutTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(notificationPane.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(notificationPane.opacityProperty(), 0.0))
        );

        fadeInTimeline.setOnFinished((e) -> {
            fadeOutTimeline.play();
        });

        fadeOutTimeline.setOnFinished((e) -> {
            stage.setScene(null);
        });

        fadeInTimeline.play();
    }
}