package com.pokemon.app.services;

import com.pokemon.app.dto.PokemonDto;
import com.pokemon.app.mapper.PokemonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class PokemonJdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void methodInit() {
    }

    public void addToPokemonTable(PokemonDto pokemonDto) {
        jdbcTemplate.update("INSERT INTO Pokemons VALUES (?, ?, ?, ?, ?, ?, ?)",
                pokemonDto.getPokemonId(), pokemonDto.getName(), pokemonDto.getHeight(), pokemonDto.getWeight(),
                pokemonDto.getBase_experience(), pokemonDto.getSpeciesUrl(), pokemonDto.getSpeciesName());
    }

    public List<PokemonDto> getPokemonList() throws IOException {
        return jdbcTemplate.query("SELECT * FROM Pokemons", new PokemonRowMapper());
    }

    public PokemonDto getPokemonWithId(String id) throws IOException {
        try {
            String query = "SELECT * FROM Pokemons WHERE idPokemon = " + id;
            PokemonDto pokemon = jdbcTemplate.queryForObject(query, new PokemonRowMapper());
            return pokemon;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}