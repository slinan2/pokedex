package slinan.pokedex.externalApi;

public class AbilitiesDTO {
    private String is_hidden;

    private AbilityDTO ability;

    private String slot;

    public String getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(String is_hidden) {
        this.is_hidden = is_hidden;
    }

    public AbilityDTO getAbility() {
        return ability;
    }

    public void setAbility(AbilityDTO ability) {
        this.ability = ability;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }
}
