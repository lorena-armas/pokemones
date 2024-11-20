package org.example.router;

import org.example.commons.PropertiesReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionServer {

  private final PropertiesReader propertiesReader;

  public ConnectionServer() {
    this.propertiesReader = new PropertiesReader();
  }

  public void start() throws IOException {
    int port = Integer.parseInt(propertiesReader.getProperty("application.port"));
    ServerSocket serverSocket = new ServerSocket(port);
    Socket currentSocket;
    while (true) {
      currentSocket = serverSocket.accept();
      new PokemonRouterTCP(currentSocket).start();
      }
  }

}
