package pl.umk.allegroworkshop.restIntroduction.repository;

import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.BookToAdd;
import pl.umk.allegroworkshop.restIntroduction.domain.model.readers.Reader;

import java.util.List;

public interface LibraryRepository {

    List<Book> getAllBooks();
    Book getBookById(Integer id);

    List<Book> getBooksByAuthor(String name, String lastName);

    Book addBookToLibrary(BookToAdd book);

    Integer removeBookFromLibrary(Integer id);

    Book borrowBook(Integer bookId, Integer readerId);

    Book returnBook(Integer bookId);

    List<Reader> getAllReaders();

    Reader getReaderById(Integer id);
}
