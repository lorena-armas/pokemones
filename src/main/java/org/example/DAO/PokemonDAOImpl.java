package org.example.DAO;

import org.example.commons.CsvReader;
import org.example.dto.PokemonDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAOImpl implements PokemonDAO{

    private final String FILE = "/pokemones/pokemons.csv";
    private static final char SEPARATOR = ',';


    @Override
    public List<PokemonDTO> findAll() throws IOException  {
        List<PokemonDTO> pokemon = new ArrayList<>();
        try {
            CsvReader.getRecords(FILE, SEPARATOR)
                    .forEach(csvRecord -> {

                        PokemonDTO pokemons = PokemonDTO.builder()
                                .name(csvRecord.get("NAME"))
                                .type(csvRecord.get("TYPE"))
                                .code(Integer.parseInt(csvRecord.get("CODE")))
                                .build();

                        pokemon.add(pokemons);
                    });
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error reading CSV", exception);
        }
        return pokemon;
    }

    @Override
    public PokemonDTO findByCode(int code) throws IOException {
        List<PokemonDTO> allPokemon = this.findAll();
        for (PokemonDTO pokemon: allPokemon) {
            if (pokemon.getCode() == code) {
                return pokemon;
            }
        }
        throw new IllegalArgumentException("No such pokemon");
    }
}
