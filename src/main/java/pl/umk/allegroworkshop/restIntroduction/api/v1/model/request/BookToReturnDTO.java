package pl.umk.allegroworkshop.restIntroduction.api.v1.model.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookToReturnDTO {
    private final Integer bookToReturnId;

    @JsonCreator
    public BookToReturnDTO(
            @JsonProperty("bookToReturnId") Integer bookToReturnId) {
        this.bookToReturnId = bookToReturnId;
    }

    public Integer getBookToReturnId() {
        return bookToReturnId;
    }
}
