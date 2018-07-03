package com.pokemon.app.service;

import com.pokemon.dto.PokemonDto;
import com.pokemon.services.PokemonService;

public class PokemonServiceImplTest implements PokemonService {


    @Override
    public String getUrlOf(String requestedData, String id) {
        return "";
    }

    @Override
    public String prettyJsonString(String type, String id) {
        return null;
    }

    @Override
    public PokemonDto getPokemonDto(String type, String id) {
        return new PokemonDto("bulbasaur", 20);
    }

}
