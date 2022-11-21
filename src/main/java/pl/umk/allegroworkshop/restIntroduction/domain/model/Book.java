package pl.umk.allegroworkshop.restIntroduction.domain.model;

public class Book implements Comparable<Book> {
    private final Integer id;
    private final String title;
    private final Author author;

    private Boolean inStock = true;

    public Book(Integer id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
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

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public int compareTo(Book o) {
        return this.id - o.id;
    }
}
