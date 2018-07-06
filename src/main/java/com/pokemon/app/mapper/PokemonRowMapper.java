package com.pokemon.app.mapper;

import com.pokemon.app.dto.PokemonDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonRowMapper implements RowMapper<PokemonDto> {
    @Override
    public PokemonDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PokemonDto pokemon = new PokemonDto();

        pokemon.setIdPokemon((long) rs.getInt("idPokemon"));
        pokemon.setName(rs.getString("name"));
        pokemon.setHeight(rs.getInt("height"));
        pokemon.setWeight(rs.getInt("weight"));
        pokemon.setBase_experience(rs.getInt("base_experience"));
        pokemon.setSpeciesUrl(rs.getString("speciesUrl"));
        pokemon.setSpeciesName(rs.getString("speciesName"));

        return pokemon;
    }
}
