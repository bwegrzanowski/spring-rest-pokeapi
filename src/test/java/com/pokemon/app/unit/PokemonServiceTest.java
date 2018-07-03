package com.pokemon.app.unit;

import com.pokemon.app.service.PokemonServiceImplTest;
import com.pokemon.services.PokemonService;
import org.junit.Assert;
import org.junit.Test;

public class PokemonServiceTest {

    PokemonService pokemonService = new PokemonServiceImplTest();

    @Test
    public void shouldReturnBulbasaur() {
        Assert.assertEquals(pokemonService.getPokemonDto("pokemon", "1").getName(),"bulbasaur");

    }

    @Test
    public void shouldNotReturnBulbasaur() {
        Assert.assertNotEquals(pokemonService.getPokemonDto("pokemon", "1").getName(),"fafa");

    }
}
