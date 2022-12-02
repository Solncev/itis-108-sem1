package com.solncev.fxml.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

    SimpleStringProperty nickname;
    SimpleIntegerProperty points;

    public User(String nickname, Integer points) {
        this.nickname = new SimpleStringProperty(nickname);
        this.points = new SimpleIntegerProperty(points);
    }

    public String getNickname() {
        return nickname.get();
    }

    public SimpleStringProperty nicknameProperty() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname.set(nickname);
    }

    public int getPoints() {
        return points.get();
    }

    public SimpleIntegerProperty pointsProperty() {
        return points;
    }

    public void setPoints(int points) {
        this.points.set(points);
    }
}
