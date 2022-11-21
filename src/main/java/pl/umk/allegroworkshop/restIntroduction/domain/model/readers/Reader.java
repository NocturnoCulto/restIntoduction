package pl.umk.allegroworkshop.restIntroduction.domain.model.readers;

import java.util.List;

public class Reader implements Comparable<Reader> {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final List<Integer> bookIdList;

    public Reader(Integer id, String firstName, String lastName, List<Integer> bookIdList) {
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

    @Override
    public int compareTo(Reader o) {
        return this.id - o.id;
    }
}
