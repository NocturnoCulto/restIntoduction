package pl.umk.allegroworkshop.restIntroduction.domain.booksService;

import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.domain.model.Book;
import pl.umk.allegroworkshop.restIntroduction.repository.BooksRepository;

import java.util.List;

@Component
public class BooksService {
    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getAllBooks() {
        return booksRepository.getAllBooks();
    }
    public Book getBookById(Integer id) {
        return booksRepository.getBookById(id);
    }
}
