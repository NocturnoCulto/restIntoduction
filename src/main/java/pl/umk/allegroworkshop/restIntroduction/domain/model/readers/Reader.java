package pl.umk.allegroworkshop.restIntroduction.domain.model.readers;

import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;

import java.util.List;

public class Reader implements Comparable<Reader> {
    private final Integer id;
    private final String name;
    private final String lastName;
    private final List<Book> booksList;

    public Reader(Integer id, String name, String lastName, List<Book> booksList) {
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

    public List<Book> getBooksList() {
        return booksList;
    }

    @Override
    public int compareTo(Reader o) {
        return this.id - o.id;
    }
}
