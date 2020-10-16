package slinan.pokedex.externalApi;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.ChainLink;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpeciesFlavorText;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@Component("pokemonApi")
public class PokeApi {

    private static final String API_URL = "https://pokeapi.co/api/v2/";
    private static final String POKEMON_RESOURCE = "pokemon/";

    private final HttpClient client;
    private final PokeApiClient pokeApi;

    PokeApi() {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        pokeApi = new PokeApiClient();
    }

    public Optional<PokemonDTO> findById(int id) {
        Optional<PokemonDTO> pokemon = Optional.empty();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + POKEMON_RESOURCE + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            PokemonDTO pokemonDTO = new Gson().fromJson(response.body(), PokemonDTO.class);
            pokemon = Optional.of(pokemonDTO);
        } catch (IllegalStateException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return pokemon;
    }

    public Optional<PokemonDTO> findByName(String name) {
        Optional<PokemonDTO> pokemon = Optional.empty();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + POKEMON_RESOURCE + name))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            PokemonDTO pokemonDTO = new Gson().fromJson(response.body(), PokemonDTO.class);
            pokemon = Optional.of(pokemonDTO);
        } catch (JsonSyntaxException | IOException | InterruptedException e) {
            //Pokemon could not bee found
        }
        return pokemon;
    }

    public Optional<PokemonDetailDTO> getPokemonDetail(int id) {
        Optional<PokemonDetailDTO> detailOption = Optional.empty();
        try {
            PokemonSpecies pokemonSpecies = pokeApi.getPokemonSpecies(id);
            PokemonDetailDTO detail = new PokemonDetailDTO();
            String description = pokemonSpecies.getFlavorTextEntries().stream()
                    .filter(t -> t.getLanguage().getName().equals("en"))
                    .map(PokemonSpeciesFlavorText::getFlavorText)
                    .collect(Collectors.joining(", "));
            int evolutionChainId = pokemonSpecies.getEvolutionChain().getId();
            Queue<ChainLink> nodesToVisit = new ArrayDeque<>();
            ChainLink evolutionChain = pokeApi.getEvolutionChain(evolutionChainId).getChain();
            nodesToVisit.add(evolutionChain);
            List<String> evolutions = new ArrayList<>();
            while(!nodesToVisit.isEmpty()) {
                ChainLink toVisit = nodesToVisit.poll();
                evolutions.add(toVisit.getSpecies().getName());
                nodesToVisit.addAll(toVisit.getEvolvesTo());
            }
            detail.setDescription(description);
            detail.setEvolutions(evolutions);
            detailOption = Optional.of(detail);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return detailOption;
    }
}
