package com.solncev.chat;

import com.solncev.chat.client.ChatClient;
import com.solncev.chat.model.UserConfig;
import com.solncev.chat.view.BaseView;
import com.solncev.chat.view.ChatView;
import com.solncev.chat.view.UserConfigView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApplication extends Application {

    Stage primaryStage;

    private BorderPane rootLayout;

    private ChatView chatView;

    private UserConfigView userConfigView;

    public UserConfig getUserConfig() {
        return userConfig;
    }

    private UserConfig userConfig;

    public ChatClient getChatClient() {
        return chatClient;
    }

    private ChatClient chatClient;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("Chat");
        this.primaryStage.setOnCloseRequest(e -> System.exit(0));

        this.chatClient = new ChatClient(this);

        BaseView.setChatApplication(this);

        this.userConfigView = new UserConfigView();
        this.chatView = new ChatView();

        this.initLayout();
    }

    private void initLayout() {
        rootLayout = new BorderPane();

        Scene scene = new Scene(rootLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        this.setView(userConfigView);
    }

    public BaseView getChatView() {
        return chatView;
    }

    public void setView(BaseView view) {
        rootLayout.setCenter(view.getView());
    }

    public void startChatClient() throws IOException {
        this.chatClient.start();
    }

    public void appendMessage(String message) {
        chatView.appendMessageToConversation(message);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }
}
