package com.pokemon.app.services;

import com.pokemon.app.dto.PokemonDto;

import java.io.IOException;
import java.util.List;

public interface PokemonService {
    String getUrlOf(String requestedData, String id);
    String prettyJsonString(String type, String id);
    PokemonDto getPokemonDto(String type, String id);

    void addToDb(PokemonDto pokemonDto);

    List<PokemonDto> getPokemons() throws IOException;

    PokemonDto ifExistsGetPokemonFromDbElseSaveToDbFromApi(String id) throws IOException;

    PokemonDto getPokemonWithId(String id) throws IOException;
}
