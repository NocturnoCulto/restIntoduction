package pl.umk.allegroworkshop.restIntroduction.api.v1.model.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookToBorrowDTO {
    private final Integer bookToBorrowId;
    private final Integer readerId;

    @JsonCreator
    public BookToBorrowDTO(
            @JsonProperty("bookToBorrowId") Integer bookToBorrowId,
            @JsonProperty("readerId") Integer readerId) {
        this.bookToBorrowId = bookToBorrowId;
        this.readerId = readerId;
    }

    public Integer getBookToBorrowId() {
        return bookToBorrowId;
    }

    public Integer getReaderId() {
        return readerId;
    }
}
