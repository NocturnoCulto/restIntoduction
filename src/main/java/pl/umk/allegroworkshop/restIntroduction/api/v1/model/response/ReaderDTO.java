package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;

import java.util.List;

public class ReaderDTO {
    private final Integer id;
    private final String name;
    private final String lastName;
    private final List<BookDTO> booksList;

    @JsonCreator
    public ReaderDTO(
            @JsonProperty("id") Integer id,
            @JsonProperty("name") String name,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("bookList") List<BookDTO> booksList) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.booksList = booksList;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public List<BookDTO> getBooksList() {
        return booksList;
    }
}
