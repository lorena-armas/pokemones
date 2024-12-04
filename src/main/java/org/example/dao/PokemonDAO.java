package org.example.dao;

import org.example.dto.PokemonDTO;
import java.io.IOException;
import java.util.List;

public interface PokemonDAO {

    List<PokemonDTO> findAll() throws IOException;

    PokemonDTO findByCode(int code) throws IOException;
}
