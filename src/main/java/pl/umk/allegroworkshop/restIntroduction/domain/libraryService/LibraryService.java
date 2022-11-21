package pl.umk.allegroworkshop.restIntroduction.domain.libraryService;

import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;
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
}
