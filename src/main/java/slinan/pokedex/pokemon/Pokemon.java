package slinan.pokedex.pokemon;

import slinan.pokedex.externalApi.PokemonDTO;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

@Entity
public class Pokemon {

    private @Id int id;
    private String name;
    private int weight;
    private String picture;
    private @ElementCollection List<String> types;
    private @ElementCollection List<String> abilities;
    private @Column(columnDefinition = "TEXT") String description;
    private @ElementCollection List<String> evolutions;

    public Pokemon() { }

    public Pokemon(int id, String name, int weight, String picture, List<String> types, List<String> abilities, String description, List<String> evolutions) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.types = types;
        this.abilities = abilities;
        this.picture = picture;
        this.description = description;
        this.evolutions = evolutions;
    }

    public Pokemon(PokemonDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.weight = dto.getWeight();
        this.picture = dto.getSprites().getFront_default() ;
        this.types = dto.getTypes().stream().map(t -> t.getType().getName()).collect(Collectors.toList());
        this.abilities = dto.getAbilities().stream().map(a -> a.getAbility().getName()).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

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
