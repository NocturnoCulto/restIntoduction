package pl.umk.allegroworkshop.restIntroduction.repository;

import org.springframework.stereotype.Repository;
import pl.umk.allegroworkshop.restIntroduction.domain.model.Author;
import pl.umk.allegroworkshop.restIntroduction.domain.model.Book;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryBooksRepository implements BooksRepository {

    private final Map<Integer, Book> library = new HashMap<>();

    @Override
    public List<Book> getAllBooks() {
        return library.values().stream().sorted().toList();
    }

    @Override
    public Book getBookById(Integer id) {
        return library.get(id);
    }

    @Override
    public List<Book> getBooksByAuthor(String name, String lastName) {
        Author author = new Author(name, lastName);
        return library.values().stream().filter(book -> Objects.equals(book.getAuthor(), author)).collect(Collectors.toList());
    }

    @Override
    public void addBookToLibrary(Book book) {
        library.put(book.getId(), book);

    }

    @Override
    public void removeBookFromLibrary(Integer id) {
        library.remove(id);

    }

    @Override
    public void borrowBook(Integer id) {

    }

    @Override
    public void returnBook(Integer id) {

    }

    @PostConstruct
    void fillLibraryForExamples() {
        library.put(123 ,new Book(123, "Hobbit", new Author("John Ronald Reuel", "Tolkien")));
        library.put(125 ,new Book(125, "Lord of the rings", new Author("John Ronald Reuel", "Tolkien")));
        library.put(130 ,new Book(130, "Potop", new Author("Henryk", "Sienkiewicz")));
        library.put(135 ,new Book(135, "Ogniem i mieczem", new Author("Henryk", "Sienkiewicz")));
        library.put(140 ,new Book(140, "Krew elfów", new Author("Andrzej", "Sapkowski")));
        library.put(145 ,new Book(145, "Czas pogardy", new Author("Andrzej", "Sapkowski")));
        library.put(150 ,new Book(150, "Chrzest ognia", new Author("Andrzej", "Sapkowski")));
        library.put(155 ,new Book(155, "Wieża Jaskółki", new Author("Andrzej", "Sapkowski")));
        library.put(160 ,new Book(160, "Pani Jeziora", new Author("Andrzej", "Sapkowski")));
    }

}
