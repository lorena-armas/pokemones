package org.example;

import org.example.router.ConnectionServer;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConnectionServer server = new ConnectionServer();
        server.start();
    }
}