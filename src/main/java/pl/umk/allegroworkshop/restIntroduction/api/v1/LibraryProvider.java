package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToBorrowDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToReturnDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.AddedBookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToAddDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToRemoveDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.RemovedBookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BooksResponse;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.ReaderDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.ReadersResponse;
import pl.umk.allegroworkshop.restIntroduction.domain.libraryService.LibraryService;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.BookToAdd;
import pl.umk.allegroworkshop.restIntroduction.domain.model.readers.Reader;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class LibraryProvider {
    private final LibraryService libraryService;

    public LibraryProvider(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public BooksResponse getAllBooks() {
        return mapBooksToResponse(libraryService.getAllBooks());
    }

    public BooksResponse getBookById(Integer id) {
        return new BooksResponse(Stream.of(mapBookToBookDTO(libraryService.getBookById(id))).filter(Objects::nonNull).toList());
    }

    public ReadersResponse getAllReaders() {
        return mapReadersToResponse(libraryService.getAllReaders());
    }

    public ReadersResponse getReaderById(Integer id) {
        return new ReadersResponse(Stream.of(mapReaderToReaderDTO(libraryService.getReaderById(id))).filter(Objects::nonNull).toList());
    }

    public AddedBookDTO addNewBook(BookToAddDTO bookToAddDTO) {
        BookToAdd bookToAdd = new BookToAdd(bookToAddDTO.getTitle(), bookToAddDTO.getAuthorFirstName(), bookToAddDTO.getAuthorLastName());
        Book addedBook = libraryService.addBook(bookToAdd);
        return new AddedBookDTO(addedBook.getId(), addedBook.getTitle(), bookToAdd.getAuthorFirstName(), bookToAdd.getAuthorLastName());
    }

    public RemovedBookDTO removeBook(BookToRemoveDTO bookToRemoveDTO) {
        Integer idToRemove = bookToRemoveDTO.getBookToRemoveId();
        return new RemovedBookDTO(libraryService.removeBook(idToRemove));
    }

    public BooksResponse borrowBook(BookToBorrowDTO bookToBorrow) {
        return new BooksResponse(Stream.of(mapBookToBookDTO(libraryService.borrowBook(bookToBorrow.getBookToBorrowId(), bookToBorrow.getReaderId()))).filter(Objects::nonNull).toList());
    }

    public BooksResponse returnBook(BookToReturnDTO bookToReturn) {
        return new BooksResponse(Stream.of(mapBookToBookDTO(libraryService.returnBook(bookToReturn.getBookToReturnId()))).filter(Objects::nonNull).toList());
    }

    private BooksResponse mapBooksToResponse(List<Book> books) {
        return new BooksResponse(books.stream().map(this::mapBookToBookDTO).toList());
    }

    private ReadersResponse mapReadersToResponse(List<Reader> readers) {
        return new ReadersResponse(readers.stream().map(this::mapReaderToReaderDTO).toList());
    }

    private ReaderDTO mapReaderToReaderDTO(Reader reader) {
        if (reader == null) return null;
        return new ReaderDTO(reader.getId(), reader.getFirstName(), reader.getLastName(), reader.getBookIdList());
    }

    private BookDTO mapBookToBookDTO(Book book) {
        if (book == null) return null;
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName(), book.getInStock(), book.getReaderId());
    }

}
