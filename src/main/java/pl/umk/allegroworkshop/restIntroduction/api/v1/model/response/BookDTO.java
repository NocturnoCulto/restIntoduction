package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private final Integer id;
    private final String title;
    private final String authorFirstName;
    private final String authorLastName;
    private final Boolean inStock;
    private final Integer readerId;
    private final DescriptionDTO description;

    @JsonCreator
    public BookDTO(@JsonProperty("id") Integer id,
                   @JsonProperty("title") String title,
                   @JsonProperty("authorFirstName") String authorFirstName,
                   @JsonProperty("authorLastName") String authorLastName,
                   @JsonProperty("inStock") Boolean inStock,
                   @JsonProperty("readerId") Integer readerId,
                   @JsonProperty("description") DescriptionDTO description) {
        this.id = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.inStock = inStock;
        this.readerId = readerId;
        this.description = description;
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

    public Boolean getInStock() {
        return inStock;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public DescriptionDTO getDescription() {
        return description;
    }
}
