package com.pokemon.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilitiesDto {
    public AbilitiesDto() {
    }

    @JsonProperty("is_hidden")
    private boolean isHidden;

    @JsonProperty("slot")
    private int slot;

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "AbilitiesDto{" +
                "isHidden=" + isHidden +
                ", slot=" + slot +
                '}';
    }
}