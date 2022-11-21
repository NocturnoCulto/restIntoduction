package pl.umk.allegroworkshop.restIntroduction.domain.libraryService;

import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.BookToAdd;
import pl.umk.allegroworkshop.restIntroduction.domain.model.readers.Reader;
import pl.umk.allegroworkshop.restIntroduction.repository.LibraryRepository;

import java.util.List;

@Component
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Book> getAllBooks() {
        return libraryRepository.getAllBooks();
    }

    public Book getBookById(Integer id) {
        return libraryRepository.getBookById(id);
    }

    public List<Reader> getAllReaders(){
        return libraryRepository.getAllReaders();
    }

    public Reader getReaderById(Integer id) {
        return libraryRepository.getReaderById(id);
    }

    public Book addBook(BookToAdd bookToAdd) {
        return libraryRepository.addBookToLibrary(bookToAdd);
    }

    public Integer removeBook(Integer idToRemove) {
        return libraryRepository.removeBookFromLibrary(idToRemove);
    }

    public Book borrowBook(Integer bookToBorrow, Integer readerId) {
        return libraryRepository.borrowBook(bookToBorrow, readerId);
    }

    public Book returnBook(Integer bookToReturnId) {
        return libraryRepository.returnBook(bookToReturnId);
    }
}
