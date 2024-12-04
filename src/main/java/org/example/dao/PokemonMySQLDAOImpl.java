package org.example.dao;

import org.example.commons.MySQLConnection;
import org.example.dto.PokemonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PokemonMySQLDAOImpl implements PokemonDAO{

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet result;

    @Override
    public List<PokemonDTO> findAll() {
        try {
            connection = MySQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT code, type, name FROM pokemones");
            result = statement.executeQuery();

            List<PokemonDTO> pokemonList = new ArrayList<>();
            while (result.next()) {
                PokemonDTO pokemonDto = new PokemonDTO();
                pokemonDto.setCode(result.getInt("code"));
                pokemonDto.setType(result.getString("type"));
                pokemonDto.setName(result.getString("name"));

                pokemonList.add(pokemonDto);
            }
            connection.commit();
            return pokemonList;

        } catch (Exception exception) {
            rollback();
            throw new RuntimeException("error to find all pokemones: " + exception.getMessage());
        } finally {
            closeResources();
        }
    }

    @Override
    public PokemonDTO findByCode(int code) {
        try {
            connection = MySQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT code, type, name FROM pokemones WHERE code = ?");
            statement.setInt(1, code);
            result = statement.executeQuery();

            PokemonDTO pokemon = new PokemonDTO();
            if (result.next()) {
                pokemon.setCode(result.getInt("code"));
                pokemon.setType(result.getString("type"));
                pokemon.setName(result.getString("name"));
            }
            connection.commit();
            return pokemon;

        } catch (Exception exception) {
            rollback();
            throw new RuntimeException("error to find pokemon by code: " + exception.getMessage());
        } finally {
            closeResources();
        }
    }


    private void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (Exception exception) {
            throw new RuntimeException("error to rollback: " + exception.getMessage());
        }
    }

    private void closeResources() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        } catch (Exception exception) {
            throw new RuntimeException("error to close resources: " + exception.getMessage());
        }
    }
}
