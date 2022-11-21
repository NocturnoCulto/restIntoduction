package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDTO {

    private final Integer id;
    private final String title;
    private final String authorName;
    private final String authorLastName;
    private final Boolean inStock;

    @JsonCreator
    public BookDTO(@JsonProperty("id") Integer id,
                   @JsonProperty("title") String title,
                   @JsonProperty("authorName") String authorName,
                   @JsonProperty("authorLastName") String authorLastName,
                   @JsonProperty("inStock") Boolean inStock) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        this.inStock = inStock;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public Boolean getInStock() {
        return inStock;
    }

}
