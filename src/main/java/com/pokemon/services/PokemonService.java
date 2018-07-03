package com.pokemon.services;

import com.pokemon.dto.PokemonDto;

public interface PokemonService {
    String getUrlOf(String requestedData, String id);
    String prettyJsonString(String type, String id);
    PokemonDto getPokemonDto(String type, String id);
}
