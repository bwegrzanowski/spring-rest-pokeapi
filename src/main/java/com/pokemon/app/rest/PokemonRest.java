package com.pokemon.app.rest;

import com.pokemon.app.dto.PokemonDto;
import com.pokemon.app.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PokemonRest {
    private PokemonService pokemonService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public PokemonRest(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @RequestMapping(value = "/api/{requestedData}", method = RequestMethod.GET)
    public PokemonDto getData(@RequestParam(value = "id") String id, @PathVariable String requestedData) {
        return pokemonService.getPokemonDto(requestedData, id);
    }

    @PostMapping(value = "/addPokemon")
    public ResponseEntity<String> addPokemon(@RequestBody PokemonDto pokemonDto) {
        pokemonService.addToDb(pokemonDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getPokemons", method = RequestMethod.GET)
    public List<PokemonDto> getPokemon() throws IOException {
        return pokemonService.getPokemons();
    }

    @RequestMapping(value = "/showPokemon/{id}", method = RequestMethod.GET)
    public ResponseEntity<PokemonDto> getPokemonFromDbOrApiAndSaveInDb(@PathVariable String id) throws IOException {
        return getPokemonDtoResponseEntity(id, pokemonService.ifExistsGetPokemonFromDbElseSaveToDbFromApi(id));
    }

    private ResponseEntity<PokemonDto> getPokemonDtoResponseEntity(@RequestParam(value = "id") String id, PokemonDto pokemonDto) throws IOException {
        pokemonDto.add(linkTo(methodOn(PokemonRest.class).getPokemonFromDbOrApiAndSaveInDb(id)).withSelfRel());
        return new ResponseEntity<>(pokemonDto, HttpStatus.OK);
    }
}
