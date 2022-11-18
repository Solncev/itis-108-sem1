package com.solncev.chat.client;

import com.solncev.chat.ChatApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {

    private Socket socket;
    private ClientThread clientThread;

    public ChatApplication getChatApplication() {
        return chatApplication;
    }

    private final ChatApplication chatApplication;

    public ChatClient(ChatApplication chatApplication) {
        this.chatApplication = chatApplication;
    }

    public void sendMessage(String message) {
        try {
            clientThread.getOutput().write(message);
            clientThread.getOutput().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() throws IOException {
        String host = chatApplication.getUserConfig().getHost();
        int port = chatApplication.getUserConfig().getPort();

        socket = new Socket(host, port);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        clientThread = new ClientThread(input, output, this);

        new Thread(clientThread).start();
    }
}
