package pl.umk.allegroworkshop.restIntroduction.repository;

import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;
import pl.umk.allegroworkshop.restIntroduction.domain.model.readers.Reader;

import java.util.List;

public interface LibraryRepository {

    List<Book> getAllBooks();
    Book getBookById(Integer id);

    List<Book> getBooksByAuthor(String name, String lastName);

    void addBookToLibrary(Book book);

    void removeBookFromLibrary(Integer id);

    void borrowBook(Integer bookId, Integer readerId);

    void returnBook(Integer bookId);

    List<Reader> getAllReaders();

    Reader getReaderById(Integer id);
}
