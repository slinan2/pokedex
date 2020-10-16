package slinan.pokedex.pokemon;

import org.springframework.stereotype.Component;
import slinan.pokedex.externalApi.PokeApi;
import slinan.pokedex.externalApi.PokemonDTO;
import slinan.pokedex.externalApi.PokemonDetailDTO;

import java.util.Optional;

@Component("pokemonService")
public class PokemonService {

    private final PokemonDAO repository;
    private final PokeApi pokemonApi;

    PokemonService(PokemonDAO repository, PokeApi pokemonApi) {
        this.repository = repository;
        this.pokemonApi = pokemonApi;
    }

    Optional<Pokemon> findById(int id) {
        Optional<Pokemon> pokemon = repository.findById(id);
        if (pokemon.isEmpty()) {
            Optional<PokemonDTO> dtoOption = pokemonApi.findById(id);
            if (dtoOption.isPresent()) {
                Pokemon newPokemon = new Pokemon(dtoOption.get());
                Optional<PokemonDetailDTO> detail = pokemonApi.getPokemonDetail(id);
                newPokemon.setDescription(detail.get().getDescription());
                newPokemon.setEvolutions(detail.get().getEvolutions());
                pokemon = Optional.of(newPokemon);
                repository.save(pokemon.get());
            }
        }
        return pokemon;
    }

    Optional<Pokemon> findByName(String name) {
        Optional<Pokemon> pokemon = repository.findByName(name);
        if (pokemon.isEmpty()) {
            Optional<PokemonDTO> dtoOption = pokemonApi.findByName(name);
            if (dtoOption.isPresent()) {
                Pokemon newPokemon = new Pokemon(dtoOption.get());
                Optional<PokemonDetailDTO> detail = pokemonApi.getPokemonDetail(dtoOption.get().getId());
                newPokemon.setDescription(detail.get().getDescription());
                newPokemon.setEvolutions(detail.get().getEvolutions());
                pokemon = Optional.of(newPokemon);
                repository.save(pokemon.get());
            }
        }
        return pokemon;
    }
}
