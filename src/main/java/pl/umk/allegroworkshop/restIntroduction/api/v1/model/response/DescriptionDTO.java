package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

public class DescriptionDTO {
    private final String shortDescription;
    private final String longDescripiton;

    public DescriptionDTO(String shortDescription, String longDescripiton) {
        this.shortDescription = shortDescription;
        this.longDescripiton = longDescripiton;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescripiton() {
        return longDescripiton;
    }
}
