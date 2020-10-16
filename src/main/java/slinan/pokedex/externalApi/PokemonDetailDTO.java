package slinan.pokedex.externalApi;

import java.util.List;

public class PokemonDetailDTO {
    private String description;
    private List<String> evolutions;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(List<String> evolutions) {
        this.evolutions = evolutions;
    }
}
