package org.example.repository;

import org.example.dao.PokemonDAO;
import org.example.dto.PokemonDTO;
import java.io.IOException;
import java.util.List;

public class PokemonRepository {

    private final PokemonDAO pokemonDAO;

    public PokemonRepository(PokemonDAO pokemonDAO) {
        this.pokemonDAO = pokemonDAO;
    }

    public List<PokemonDTO> findAll() throws IOException {
        return pokemonDAO.findAll();
    }

    public PokemonDTO findByCode(int code) throws IOException {
        return pokemonDAO.findByCode(code);
    }
}
