package org.example.dao;

import org.example.commons.repository.CsvRepository;
import org.example.dto.PokemonDTO;

public class PokemonCsvDAOImpl extends CsvRepository implements PokemonDAO  {

    private static final String FILE = "/pokemones/pokemons.csv";

    public PokemonCsvDAOImpl () {
        super(FILE, PokemonDTO.class);
    }

    @Override
    public PokemonDTO findByCode(Integer code) {
        return (PokemonDTO) super.findByCode(code);
    }

    @Override
    public boolean supports(Class<?> selectedCass) {
        return PokemonCsvDAOImpl.class.isAssignableFrom(selectedCass);
    }
}
