package org.example.service;

import org.example.dto.PokemonDTO;
import org.example.repository.PokemonRepository;
import java.io.IOException;
import java.util.List;

public class PokemonServiceImpl implements PokemonService{

    private final PokemonRepository repository;

    public PokemonServiceImpl() {
        this.repository = new PokemonRepository();
    }

    @Override
    public List<PokemonDTO> findAll() throws IOException {
        List<PokemonDTO> pokemonList = repository.findAll();
        return pokemonList;
    }

    @Override
    public PokemonDTO findByCode(int code) throws IOException {
        PokemonDTO pokemonCode = repository.findByCode(code);
        return pokemonCode;
    }


}
