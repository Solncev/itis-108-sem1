package com.solncev.udp;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import static com.solncev.udp.GreetingServer.PORT;

public class GreetingClient {

    private DatagramSocket socket;
    private byte[] buffer;
    private final InetAddress address;

    public GreetingClient() throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String send(String message) throws IOException {
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
        socket.send(packet);

        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        return new String(packet.getData(), 0, packet.getLength());
    }

    public void stopClient() {
        socket.close();
    }
}
