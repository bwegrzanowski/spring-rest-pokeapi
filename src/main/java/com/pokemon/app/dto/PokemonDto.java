package com.pokemon.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.hateoas.ResourceSupport;

import java.util.Arrays;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDto extends ResourceSupport {

    private Long idPokemon;

    private String name;
    private Integer height;
    private Integer weight;
    private Integer base_experience;

//species
    private String speciesUrl;
    private String speciesName;

    @Transient
    @JsonProperty("abilities")
    private AbilitiesDto[] abilities;

    @Transient
    @JsonProperty("stats")
    private StatsDto[] stats;

    @SuppressWarnings("unchecked")
    @JsonProperty("species")
    private void unpackNestedInSpecies(Map<String, Object> spec) {
        this.speciesName = (String) spec.get("name");
        this.speciesUrl = (String) spec.get("url");
    }

    public PokemonDto(Long idPokemon, String name, Integer weight) {
        this.idPokemon = idPokemon;
        this.name = name;
        this.weight = weight;
    }

    public PokemonDto(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public PokemonDto() {
    }

    public Long getPokemonId() {
        return idPokemon;
    }

    public void setIdPokemon(Long idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(Integer base_experience) {
        this.base_experience = base_experience;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSpeciesUrl() {
        return speciesUrl;
    }

    public void setSpeciesUrl(String speciesUrl) {
        this.speciesUrl = speciesUrl;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    @Override
    public String toString() {
        return "PokemonDto{" +
                "idPokemon=" + idPokemon +
                ", name='" + name + '\'' +
                ", base_experience=" + base_experience +
                ", height=" + height +
                ", weight=" + weight +
                ", speciesUrl='" + speciesUrl + '\'' +
                ", speciesName='" + speciesName + '\'' +
                ", abilities=" + Arrays.toString(abilities) +
                ", stats=" + Arrays.toString(stats) +
                '}';
    }
}
