package pl.umk.allegroworkshop.restIntroduction.domain.model.books;

public class Description {
    private final String shortDescription;
    private final String longDescription;

    public Description(String shortDescription, String longDescription) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }
}
