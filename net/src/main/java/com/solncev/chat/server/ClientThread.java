package com.solncev.chat.server;

import com.solncev.chat.client.ChatClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClientThread implements Runnable {

    private final BufferedReader input;
    private final BufferedWriter output;
    private ChatServer chatServer;

    public BufferedReader getInput() {
        return input;
    }

    public BufferedWriter getOutput() {
        return output;
    }

    public ClientThread(BufferedReader input, BufferedWriter output, ChatServer chatServer) {
        this.input = input;
        this.output = output;
        this.chatServer = chatServer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = input.readLine();
                chatServer.sendMessage(message, this);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
