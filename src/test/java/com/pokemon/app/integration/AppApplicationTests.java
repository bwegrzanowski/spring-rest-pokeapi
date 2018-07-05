package com.pokemon.app.integration;

import com.pokemon.app.dto.PokemonDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AppApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;


	@Test
	public void shouldGetResponseBody() {
		PokemonDto body = this.testRestTemplate.getForObject("/pokemon?id=1",PokemonDto.class);
		assertThat(body.getName()).isEqualTo("bulbasaur");
	}

	@Test
	public void shouldGetStatus200() {
		ResponseEntity<PokemonDto> response
				= testRestTemplate.getForEntity("/pokemon?id=1", PokemonDto.class);
		//one way to do tests
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
	}

	@Test
	public void shouldGetStatus500() {
		ResponseEntity<PokemonDto> response
				= testRestTemplate.getForEntity("/pokemo?id=1", PokemonDto.class);
		//other way to do tests
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Test
	public void shouldGetStatus404() {
		ResponseEntity<PokemonDto> response
				= testRestTemplate.getForEntity("/", PokemonDto.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

}

