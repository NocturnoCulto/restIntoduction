package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BooksResponse;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.ReaderDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.ReadersResponse;
import pl.umk.allegroworkshop.restIntroduction.domain.libraryService.LibraryService;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;
import pl.umk.allegroworkshop.restIntroduction.domain.model.readers.Reader;

import java.util.Collections;
import java.util.List;

@Component
public class ResponseProvider {
    private final LibraryService libraryService;

    public ResponseProvider(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public BooksResponse getAllBooks() {
        return mapBooksToResponse(libraryService.getAllBooks());
    }

    public BooksResponse getBookById(Integer id) {
        return new BooksResponse(Collections.singletonList(mapBookToBookDTO(libraryService.getBookById(id))));
    }

    public ReadersResponse getAllReaders() {
        return mapReadersToResponse(libraryService.getAllReaders());
    }

    public ReadersResponse getReaderById(Integer id) {
        return new ReadersResponse(Collections.singletonList(mapReaderToReaderDTO(libraryService.getReaderById(id))));
    }
    private BooksResponse mapBooksToResponse(List<Book> books) {
        return new BooksResponse(books.stream().map(this::mapBookToBookDTO).toList());
    }

    private ReadersResponse mapReadersToResponse(List<Reader> readers) {
        return new ReadersResponse(readers.stream().map(this::mapReaderToReaderDTO).toList());
    }

    private ReaderDTO mapReaderToReaderDTO(Reader reader) {
        return new ReaderDTO(reader.getId(), reader.getName(), reader.getLastName(), reader.getBooksList().stream().map(this::mapBookToBookDTO).toList());
    }

    private BookDTO mapBookToBookDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getAuthor().getLastName(), book.getInStock());
    }

}
