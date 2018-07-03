package com.pokemon.cache;

import com.pokemon.dto.PokemonDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonCache {
    public List<PokemonDto> pokemonList;

    @PostConstruct
    public void methodInit() {
        pokemonList = new ArrayList<>();
    }


}
