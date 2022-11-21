package pl.umk.allegroworkshop.restIntroduction.api.v1.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddedBookDTO {
    private final Integer id;
    private final String title;
    private final String authorFirstName;
    private final String authorLastName;

    @JsonCreator
    public AddedBookDTO(@JsonProperty("id") Integer id,
                        @JsonProperty("title") String title,
                        @JsonProperty("authorFirstName") String authorFirstName,
                        @JsonProperty("authorLastName") String authorLastName) {
        this.id = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public Integer getId() {
        return id;
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
