package org.example.router;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PokemonRouterTCP extends Thread {

  private final PokemonHandler pokemonHandler;

  private final Socket socket;

  public PokemonRouterTCP(Socket socket) {
    this.pokemonHandler = new PokemonHandler();
    this.socket = socket;
  }

  public void run() {
    try(
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true)
    ) {
      String operation = inputReader.readLine();

      if(operation.matches("^pokemon/\\d{3}$")) {
        String pokemonCode = operation.split("/")[1].trim();
        pokemonHandler.findByCode(Integer.parseInt(pokemonCode), outputWriter);
      }

      if(operation.equals("/pokemones")) {
        pokemonHandler.findAll(outputWriter);
      }

    } catch (Exception exception) {
      throw new IllegalArgumentException("Operation not found: " + exception.getMessage());

    } finally {
      try {
        if (socket != null) socket.close();
      } catch (Exception exception) {
        throw new RuntimeException("Error closing TCP connection: " + exception.getMessage());
      }
    }
  }

}
