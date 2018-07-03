package com.pokemon.repositories;

import com.pokemon.dto.PokemonDto;
import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<PokemonDto, Long> {
}
