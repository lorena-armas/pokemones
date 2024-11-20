package org.example;

import org.example.dto.PokemonDTO;
import org.example.service.PokemonService;
import org.example.service.PokemonServiceImpl;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PokemonService pokemonService = new PokemonServiceImpl();

        for (PokemonDTO pokemonDTO : pokemonService.findAll()){
            System.out.println(pokemonDTO);
        }

    }
}