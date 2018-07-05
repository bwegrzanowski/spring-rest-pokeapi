package com.pokemon.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemon.app.StatsDto;
import org.springframework.data.annotation.Transient;

import java.util.Arrays;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDto {

    private Long id;

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

    public PokemonDto(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public PokemonDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
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
