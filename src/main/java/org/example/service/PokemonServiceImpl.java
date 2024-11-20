package org.example.service;

import org.example.dto.PokemonDTO;
import org.example.mapper.PokemonMapper;
import org.example.repository.PokemonRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PokemonServiceImpl implements PokemonService{

    private final PokemonMapper mapper;
    private final PokemonRepository repository;

    public PokemonServiceImpl(PokemonMapper mapper, PokemonRepository pokemonRepository) {
        this.mapper = mapper;
        this.repository = pokemonRepository;
    }


    @Override
    public List<PokemonDTO> findAll() throws IOException {
        List<PokemonDTO> pokemonDTOS = repository.findAll();
        return Collections.singletonList(mapper.toDTO((PokemonDTO) pokemonDTOS));
    }
}
