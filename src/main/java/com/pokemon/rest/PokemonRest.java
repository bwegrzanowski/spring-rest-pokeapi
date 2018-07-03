package com.pokemon.rest;

import com.pokemon.cache.PokemonCache;
import com.pokemon.dto.PokemonDto;
import com.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
public class PokemonRest {
    private PokemonService pokemonService;
    private PokemonCache pokemonCache;

    @Autowired
    public PokemonRest(PokemonCache pokemonCache, PokemonService pokemonService) {
        this.pokemonService = pokemonService;
        this.pokemonCache = pokemonCache;
    }


    @RequestMapping(value = "/app/{requestedData}", method = RequestMethod.GET)
    public PokemonDto getData(@RequestParam(value = "id") String id, @PathVariable String requestedData) {
        return pokemonService.getPokemonDto(requestedData, id);
    }
    @PostMapping(value = "/addPokemon")
    public ResponseEntity<String> addPokemon(@RequestBody PokemonDto pokemon) {
        pokemonCache.pokemonList.add(pokemon);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getPokemons", method = RequestMethod.GET)
    public String getAllPokemons() {
        Iterator<PokemonDto> iterator = pokemonCache.pokemonList.iterator();
        return iterator.next().toString();
    }

}
