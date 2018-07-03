package com.pokemon.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.dto.PokemonDto;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class PokemonServiceImpl implements PokemonService {

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
}
