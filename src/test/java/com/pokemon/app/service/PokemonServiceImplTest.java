package com.pokemon.app.service;

import com.pokemon.app.dto.PokemonDto;
import com.pokemon.app.services.PokemonService;

import java.io.IOException;
import java.util.List;

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

    @Override
    public void addToDb(PokemonDto pokemonDto) {

    }

    @Override
    public List<PokemonDto> getPokemons() throws IOException {
        return null;
    }

    @Override
    public PokemonDto ifExistsGetPokemonFromDbElseSaveToDbFromApi(String id) throws IOException {
        return null;
    }

    @Override
    public PokemonDto getPokemonWithId(String id) throws IOException {
        return null;
    }

}
