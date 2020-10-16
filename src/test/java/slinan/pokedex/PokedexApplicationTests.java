package slinan.pokedex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import slinan.pokedex.pokemon.Pokemon;
import slinan.pokedex.pokemon.PokemonController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokedexApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PokemonController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void serverIsRunning() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Pokedex API is running");
	}

	@Test
	public void getNonExistentPokemonById() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/api/pokemon/" + 9999,  HttpMethod.GET, entity, String.class );
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void getNonExistentPokemonByName() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/api/pokemon?name=" + "modyo",  HttpMethod.GET, entity, String.class );
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void badRequestPokemonById() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/api/pokemon/" + "pikachu",  HttpMethod.GET, entity, String.class );
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void getPokemonById() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Pokemon> response = this.restTemplate.exchange("http://localhost:" + port + "/api/pokemon/" + 15,  HttpMethod.GET, entity, Pokemon.class );
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isNotNull();
		assertThat(response.getBody().getWeight()).isNotNull();
		assertThat(response.getBody().getPicture()).isNotNull();
		assertThat(response.getBody().getAbilities()).isNotNull();
		assertThat(response.getBody().getDescription()).isNotNull();
		assertThat(response.getBody().getEvolutions()).isNotNull();
		assertThat(response.getBody().getTypes()).isNotNull();
	}

	@Test
	public void getPokemonByName() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Pokemon> response = this.restTemplate.exchange("http://localhost:" + port + "/api/pokemon?name=" + "pikachu",  HttpMethod.GET, entity, Pokemon.class );
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isNotNull();
		assertThat(response.getBody().getWeight()).isNotNull();
		assertThat(response.getBody().getPicture()).isNotNull();
		assertThat(response.getBody().getAbilities()).isNotNull();
		assertThat(response.getBody().getDescription()).isNotNull();
		assertThat(response.getBody().getEvolutions()).isNotNull();
		assertThat(response.getBody().getTypes()).isNotNull();
	}

}
