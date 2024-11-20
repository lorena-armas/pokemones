package org.example;

import org.example.dao.PokemonDAO;
import org.example.dao.PokemonDAOImpl;
import org.example.dto.PokemonDTO;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PokemonDAO pokemonDAO = new PokemonDAOImpl();

        for (PokemonDTO pokemon : pokemonDAO.findAll() ){
            System.out.println(pokemon);
        }
    }
}