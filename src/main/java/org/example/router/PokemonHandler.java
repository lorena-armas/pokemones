package org.example.router;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PokemonDTO;
import org.example.service.PokemonService;
import org.example.service.PokemonServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PokemonHandler {

  private final static ObjectMapper objectMapper = new ObjectMapper();
  private final PokemonService pokemonService;

  public PokemonHandler() {
    pokemonService = new PokemonServiceImpl();
  }

  public void findAll(PrintWriter output) throws IOException {
    List<PokemonDTO> pokemonList = pokemonService.findAll();
    String pokemonListJSON = objectMapper.writeValueAsString(pokemonList);

    output.println(pokemonListJSON);
  }

  public void findByCode(int pokemonCode,PrintWriter output) throws IOException {
    PokemonDTO pokemon = pokemonService.findByCode(pokemonCode);
    String pokemonJson = objectMapper.writeValueAsString(pokemon);

    output.println(pokemonJson);
  }

}
