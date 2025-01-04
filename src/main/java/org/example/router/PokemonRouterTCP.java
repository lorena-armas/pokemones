package org.example.router;

import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PokemonRouterTCP extends Thread {

  private final PokemonHandler pokemonHandler;
  private Socket socket;

  @Inject
  public PokemonRouterTCP(PokemonHandler pokemonHandler) {
    this.pokemonHandler = pokemonHandler;
  }

  public void setSocket(Socket socket){
    this.socket = socket;
  }

  public void run() {
    try(
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true)
    ) {
      String endpoint = inputReader.readLine();

      if(endpoint.matches("^pokemones/\\d{3}$")) {
        String pokemonCode = endpoint.split("/")[1].trim();
        pokemonHandler.findByCode(Integer.parseInt(pokemonCode), outputWriter);
      }

      if(endpoint.equals("pokemones/")) {
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
