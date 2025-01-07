package org.example.service;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.PokemonDTO;
import org.example.repository.PokemonRepository;
import java.io.IOException;
import java.util.List;
@Slf4j
public class PokemonServiceImpl implements PokemonService{

    private final PokemonRepository repository;

    @Inject
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.repository = pokemonRepository;
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
