package slinan.pokedex.pokemon;

import org.springframework.web.bind.annotation.*;

@RestController
public class PokemonController {

    private final PokemonService pokemonService;

    PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping(value = "/api/pokemon")
    Pokemon one(@RequestParam String name) {
        return pokemonService.findByName(name).orElseThrow(() -> new PokemonNotFoundException(name));
    }

    @GetMapping(value = "/api/pokemon/{id}")
    Pokemon one(@PathVariable int id) {
        return pokemonService.findById(id).orElseThrow(() -> new PokemonNotFoundException(id));
    }
}
