package org.example.mapper;

import org.example.dto.PokemonDTO;

public class PokemonMapper {

    public PokemonDTO toDTO(PokemonDTO pokemon) {
        return PokemonDTO.builder()
                .type(pokemon.getType())
                .name(pokemon.getName())
                .code(pokemon.getCode())
                .build();
    }
}
