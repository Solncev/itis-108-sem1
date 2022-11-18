package com.solncev.chat.view;

import com.solncev.chat.ChatApplication;
import com.solncev.chat.model.UserConfig;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class UserConfigView extends BaseView {

    private AnchorPane pane;
    private TextField name;
    private TextField host;
    private TextField port;
    private Button start;
    private VBox vBox;

    private final ChatApplication application = BaseView.getChatApplication();

    private final EventHandler<ActionEvent> startEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == start) {
                UserConfig userConfig = new UserConfig();

                userConfig.setName(name.getText());
                userConfig.setHost(host.getText());
                userConfig.setPort(Integer.parseInt(port.getText()));
                application.setUserConfig(userConfig);

                try {
                    application.startChatClient();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                application.setView(application.getChatView());
            }
        }
    };

    public UserConfigView() throws Exception {
    }


    @Override
    public Parent getView() {
        if (pane == null) {
            createView();
        }
        return pane;
    }

    private void createView() {
        pane = new AnchorPane();

        vBox = new VBox(10);

        Label nameLabel = new Label("name");
        name = new TextField();

        Label hostLabel = new Label("host");
        host = new TextField();
        host.setText("127.0.0.1");

        Label portLabel = new Label("port");
        port = new TextField();
        port.setText("5555");

        vBox.getChildren().addAll(nameLabel, name, hostLabel, host, portLabel, port);

        start = new Button("start messaging");
        start.setOnAction(startEvent);

        vBox.getChildren().addAll(start);

        AnchorPane.setTopAnchor(vBox, 50.0);
        AnchorPane.setLeftAnchor(vBox, 100.0);
        AnchorPane.setRightAnchor(vBox, 100.0);

        pane.getChildren().addAll(vBox);
    }
}
