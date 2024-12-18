package org.example.repository;

import com.google.inject.Inject;
import org.example.commons.PropertiesReader;
import org.example.dao.PokemonDAO;
import org.example.dao.PokemonCsvDAOImpl;
import org.example.dao.PokemonMySQLDAOImpl;
import org.example.dto.PokemonDTO;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class PokemonRepository {

    private final Set<PokemonDAO> pokemonDAOSet;

    @Inject
    public PokemonRepository(Set<PokemonDAO> pokemonDAOSet) {
        this.pokemonDAOSet = pokemonDAOSet;
    }

    public List<PokemonDTO> findAll() throws IOException {
        return selectDAO().findAll();
    }

    public PokemonDTO findByCode(int code) throws IOException {
        return selectDAO().findByCode(code);
    }

    private PokemonDAO selectDAO() {
        Class<?> selectedClass = PropertiesReader.getPropertyClass("pokemones.dao.selector-class");
        for(PokemonDAO dao: this.pokemonDAOSet) {
            if(dao.supports(selectedClass))
                return dao;
        }
        throw new IllegalArgumentException("No such DAO");
    }


}
