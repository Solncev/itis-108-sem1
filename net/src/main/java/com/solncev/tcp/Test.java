package com.solncev.tcp;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        GreetingClient client = new GreetingClient();
        client.startClient("127.0.0.1", 5555);

        System.out.println(client.sendMessage("Hello"));
        System.out.println(client.sendMessage("HellO"));
        System.out.println(client.sendMessage("1234"));
        System.out.println(client.sendMessage("bye"));


        System.out.println(client.sendMessage("123"));


        client.stopClient();
    }
}
