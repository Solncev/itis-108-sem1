package com.solncev.fxml.controller;

import com.solncev.fxml.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.stream.Collectors;

public class PageController {

    @FXML
    private TextField nickname;

    @FXML
    private Button search;

    @FXML
    private TableView tableView;

    @FXML
    private Label label;

    @FXML
    private VBox users;


    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<User> resultList = FXCollections.observableArrayList();

    public PageController() {
        userList.add(new User("ivan", 50));
        userList.add(new User("stepan", 60));
        userList.add(new User("boris", 70));
    }

    @FXML
    private void initialize() {
        search.setText("Search");
        search.setStyle("-fx-background-color: #ff0000");

        search.setOnAction(event -> loadData());

        nickname.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                loadData();
            }
        });

        nickname.textProperty().addListener(((observable, oldValue, newValue) -> {
            label.setText(newValue);
        }));

        initTable();
    }

    private void initTable() {
        tableView = new TableView(FXCollections.observableList(userList));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn nickname = new TableColumn("NICKNAME");
        nickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));

        TableColumn points = new TableColumn("POINTS");
        points.setCellValueFactory(new PropertyValueFactory<>("points"));

        tableView.getColumns().addAll(nickname, points);

        users.getChildren().add(tableView);
    }


    private void loadData() {
        String searchText = nickname.getText();

        Task<ObservableList<User>> task = new Task<>() {
            @Override
            protected ObservableList<User> call() {
                return FXCollections.observableArrayList(
                        userList.stream()
                                .filter(u -> u.getNickname().toLowerCase().contains(searchText.toLowerCase()))
                                .collect(Collectors.toList())
                );
            }
        };

        task.setOnSucceeded(event -> {
            resultList = task.getValue();
            tableView.setItems(resultList);
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
