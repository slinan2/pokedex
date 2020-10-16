package slinan.pokedex.pokemon;

public class PokemonNotFoundException extends RuntimeException {

    PokemonNotFoundException(int id) {
        super("Could not find pokemon " + id);
    }

    PokemonNotFoundException(String name) {
        super("Could not find pokemon " + name);
    }
}
