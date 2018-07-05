package com.pokemon.app.repositories;

import com.pokemon.app.dto.PokemonDto;
import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<PokemonDto, Long> {
}
