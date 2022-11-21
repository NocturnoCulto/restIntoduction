package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReaderDTO {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final List<Integer> bookIdList;

    @JsonCreator
    public ReaderDTO(
            @JsonProperty("id") Integer id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("bookList") List<Integer> bookIdList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookIdList = bookIdList;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Integer> getBookIdList() {
        return bookIdList;
    }
}
