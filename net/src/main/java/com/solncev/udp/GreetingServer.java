package com.solncev.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class GreetingServer extends Thread {

    public static final int PORT = 5556;
    private final DatagramSocket socket;
    private boolean alive = true;
    private final byte[] buffer = new byte[512];

    public GreetingServer() throws SocketException {
        this.socket = new DatagramSocket(PORT);
    }

    public void run() {
        while (alive) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                // logic

                byte [] data = message.getBytes();
                packet =  new DatagramPacket(data, data.length, address, port);
                socket.send(packet);

                if ("bye".equalsIgnoreCase(message.trim())) {
                    alive = false;
                }
;            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        socket.close();
    }
}
