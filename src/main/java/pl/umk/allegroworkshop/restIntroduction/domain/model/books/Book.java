package pl.umk.allegroworkshop.restIntroduction.domain.model.books;

import pl.umk.allegroworkshop.restIntroduction.domain.model.readers.Reader;

public class Book implements Comparable<Book> {
    private final Integer id;
    private final String title;
    private final Author author;
    private final  Boolean inStock;
    private final  Integer readerId;
    private final Description description;

    public Book(Integer id, String title, Author author, Boolean inStock, Integer readerId, Description description) {
        this.id = id;
        this.title = title;
        this.author = author;
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

    public Author getAuthor() {
        return author;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public int compareTo(Book o) {
        return this.id - o.id;
    }
}
