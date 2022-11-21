package pl.umk.allegroworkshop.restIntroduction.repository;

import pl.umk.allegroworkshop.restIntroduction.domain.model.Book;

import java.util.List;

public interface BooksRepository {

    List<Book> getAllBooks();
    Book getBookById(Integer id);

    List<Book> getBooksByAuthor(String name, String lastName);

    void addBookToLibrary(Book book);

    void removeBookFromLibrary(Integer id);

    void borrowBook(Integer id);

    void returnBook(Integer id);
}
