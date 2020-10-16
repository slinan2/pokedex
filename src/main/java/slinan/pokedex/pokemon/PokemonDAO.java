package slinan.pokedex.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface PokemonDAO extends JpaRepository<Pokemon, Integer> {

    Optional<Pokemon> findByName(String name);
}
