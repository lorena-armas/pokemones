package org.example.service;

import org.example.dto.PokemonDTO;

import java.io.IOException;
import java.util.List;

public interface PokemonService {

    List<PokemonDTO> findAll() throws IOException;
}
