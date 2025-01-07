package org.example.dao;

import org.example.dto.PokemonDTO;
import java.util.List;

public interface PokemonDAO {

    List<PokemonDTO> findAll();

    PokemonDTO findByCode(Integer code);

    boolean supports(Class<?> selectedCass);
}
