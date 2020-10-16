package slinan.pokedex.externalApi;

public class FlavorTextEntriesDTO {
    private LanguageDTO language;

    private String flavorText;

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }
}
