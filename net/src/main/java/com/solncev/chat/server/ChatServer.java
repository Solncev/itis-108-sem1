package com.solncev.chat.server;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private static final int PORT = 5555;
    private ServerSocket socket;
    private final List<ClientThread> clients = new ArrayList<>();

    public void start() throws IOException {
        socket = new ServerSocket(PORT);

        while (true) {
            Socket clientSocket = socket.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));

            ClientThread clientThread = new ClientThread(input, output, this);
            clients.add(clientThread);

            new Thread(clientThread).start();
        }
    }


    public void sendMessage(String message, ClientThread sender) throws IOException {
        for (ClientThread thread : clients) {
            if (thread.equals(sender)) {
                continue;
            }

            thread.getOutput().write(message + "\n");
            thread.getOutput().flush();
        }
    }


    public static void main(String[] args) throws IOException {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }
}
