package pl.umk.allegroworkshop.restIntroduction.api.v1.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookToRemoveDTO {
    private final Integer bookToRemoveId;

    @JsonCreator
    public BookToRemoveDTO(@JsonProperty("bookToRemoveId") Integer bookToRemoveId) {
        this.bookToRemoveId = bookToRemoveId;
    }

    public Integer getBookToRemoveId() {
        return bookToRemoveId;
    }
}
