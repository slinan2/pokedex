package slinan.pokedex.externalApi;

import java.util.List;

public class PokemonDTO {

    private List<TypesDTO> types;

    private int weight;

    private SpritesDTO sprites;

    private List<AbilitiesDTO> abilities;

    private String name;

    private int id;

    private int height;

    private String order;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<TypesDTO> getTypes() {
        return types;
    }

    public void setTypes(List<TypesDTO> types) {
        this.types = types;
    }

    public SpritesDTO getSprites() {
        return sprites;
    }

    public void setSprites(SpritesDTO sprites) {
        this.sprites = sprites;
    }

    public List<AbilitiesDTO> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilitiesDTO> abilities) {
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
