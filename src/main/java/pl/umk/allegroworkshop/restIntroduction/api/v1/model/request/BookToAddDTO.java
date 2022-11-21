package pl.umk.allegroworkshop.restIntroduction.api.v1.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookToAddDTO {
    private final String title;
    private final String authorFirstName;
    private final String authorLastName;

    @JsonCreator
    public BookToAddDTO(
            @JsonProperty("title") String title,
            @JsonProperty("authorFirstName") String authorFirstName,
            @JsonProperty("authorLastName") String authorLastName) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }
}
