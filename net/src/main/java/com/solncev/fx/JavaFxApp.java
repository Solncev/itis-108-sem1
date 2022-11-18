package com.solncev.fx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Optional;

public class JavaFxApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Line line = new Line();
        line.setStartX(50);
        line.setStartY(50);
        line.setEndX(150);
        line.setEndY(150);

        Text text = new Text();
        text.setFont(new Font(40));
        text.setX(100);
        text.setY(200);
        text.setText("Test text");

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Some form");
        GridPane pane = new GridPane();

        ButtonType connect = new ButtonType("Connect", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(connect);

        TextField username = new TextField();
        username.setPromptText("Username");
        pane.add(username, 1, 0);
        dialog.getDialogPane().setContent(pane);

        dialog.setResultConverter(button -> {
            if (button.equals(connect)) {
                return username.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(System.out::println);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");

        Image image = new Image(new FileInputStream("/resources/**"));
        ImageView imageView = new ImageView(image);

        Button click = new Button("click me", imageView);
        click.setOnAction(event -> alert.show());

        Group root = new Group();

        ObservableList children = root.getChildren();

        children.addAll(line, text, click);

        Scene main = new Scene(root, 600, 600);
        primaryStage.setTitle("This is my first javafx application");
        primaryStage.setScene(main);
        primaryStage.show();

        final KeyCombination kb = new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN);

        main.setOnKeyPressed(key -> {
            if (kb.match(key)) {

            }
            switch (key.getCode()) {
                case A -> alert.show();
                case Q -> primaryStage.close();
//                case ESCAPE ->
            }
        });
    }
}
