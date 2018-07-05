package com.pokemon.app.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.app.dto.PokemonDto;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements com.pokemon.app.services.PokemonService {

    com.pokemon.app.services.PokemonJdbcService pokemonJdbcService;

    @Autowired
    public PokemonServiceImpl(com.pokemon.app.services.PokemonJdbcService pokemonJdbcService) {
        this.pokemonJdbcService = pokemonJdbcService;
    }

    public String getUrlOf(String of, String id) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        ResponseEntity<String> response
                = new RestTemplate(requestFactory).exchange(
                "http://pokeapi.co/api/v2/" + of + "/" + id, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    public PokemonDto getPokemonDto(String type, String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(getUrlOf(type, id), PokemonDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addToDb(PokemonDto pokemonDto) {
        pokemonJdbcService.addToPokemonTable(pokemonDto);
    }

    @Override
    public List<PokemonDto> getPokemons() throws IOException {
        return pokemonJdbcService.getPokemonList();
    }
    @Override
    public PokemonDto getPokemonWithId(String id) throws IOException {
        return pokemonJdbcService.getPokemonWithId(id);
    }


    public String prettyJsonString(String type, String id) {
        String pokemonAsString = getPokemonDto(type, id).toString();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pokemonAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PokemonDto ifExistsGetPokemonFromDbElseSaveToDbFromApi(String id) throws IOException {
        Optional<PokemonDto> optionalPokemonDto = Optional.ofNullable(getPokemonWithId(id));
        if (optionalPokemonDto.isPresent()) {
            return optionalPokemonDto.get();
        } else {
            PokemonDto pokemon = getPokemonDto("pokemon", id);
            addToDb(pokemon);
            return pokemon;
        }
    }
}
