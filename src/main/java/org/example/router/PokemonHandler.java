package org.example.router;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import org.example.dto.PokemonDTO;
import org.example.service.PokemonService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class PokemonHandler {

  private final ObjectMapper objectMapper;
  private final PokemonService pokemonService;

  @Inject
  public PokemonHandler(ObjectMapper objectMapper, PokemonService pokemonService) {
    this.pokemonService = pokemonService;
    this.objectMapper = objectMapper;
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
