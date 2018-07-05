package com.pokemon.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsDto {

    @JsonProperty("base_stat")
    private int baseStat;

    @JsonProperty("effort")
    private int effort;

    public StatsDto() {
    }

    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    @Override
    public String toString() {
        return "StatsDto{" +
                "baseStat=" + baseStat +
                ", effort=" + effort +
                '}';
    }
}
