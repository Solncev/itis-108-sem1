package com.solncev.udp;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        new GreetingServer().start();

        GreetingClient client = new GreetingClient();

        System.out.println(client.send("Hello"));
        System.out.println(client.send("bye"));

        client.stopClient();
    }
}
