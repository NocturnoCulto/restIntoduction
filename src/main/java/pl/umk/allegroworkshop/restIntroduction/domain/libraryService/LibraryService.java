package pl.umk.allegroworkshop.restIntroduction.domain.libraryService;

import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToBorrowDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToReturnDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.*;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToAddDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToRemoveDTO;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.BookToAdd;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Description;
import pl.umk.allegroworkshop.restIntroduction.domain.model.readers.Reader;
import pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore.DescriptionStoreService;
import pl.umk.allegroworkshop.restIntroduction.repository.LibraryRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final DescriptionStoreService descriptionStoreService;


    public LibraryService(LibraryRepository libraryRepository,
                          DescriptionStoreService descriptionStoreService) {
        this.libraryRepository = libraryRepository;
        this.descriptionStoreService = descriptionStoreService;
    }

    public BooksResponse getAllBooks() {
        return mapBooksToResponse(libraryRepository.getAllBooks());
    }

    public BooksResponse getBookById(Integer id) {
        return new BooksResponse(Stream.of(mapBookToBookDTO(libraryRepository.getBookById(id), descriptionStoreService.getDescriptionById(id))).filter(Objects::nonNull).toList());
    }

    public ReadersResponse getAllReaders() {
        return mapReadersToResponse(libraryRepository.getAllReaders());
    }

    public ReadersResponse getReaderById(Integer id) {
        return new ReadersResponse(Stream.of(mapReaderToReaderDTO(libraryRepository.getReaderById(id))).filter(Objects::nonNull).toList());
    }

    public AddedBookDTO addNewBook(BookToAddDTO bookToAddDTO) {
        BookToAdd bookToAdd = new BookToAdd(bookToAddDTO.getTitle(), bookToAddDTO.getAuthorFirstName(), bookToAddDTO.getAuthorLastName());
        Book addedBook = libraryRepository.addBookToLibrary(bookToAdd);
        return new AddedBookDTO(addedBook.getId(), addedBook.getTitle(), bookToAdd.getAuthorFirstName(), bookToAdd.getAuthorLastName());
    }

    public RemovedBookDTO removeBook(BookToRemoveDTO bookToRemoveDTO) {
        Integer idToRemove = bookToRemoveDTO.getBookToRemoveId();
        return new RemovedBookDTO(libraryRepository.removeBookFromLibrary(idToRemove));
    }

    public BooksResponse borrowBook(BookToBorrowDTO bookToBorrow) {
        return new BooksResponse(Stream.of(mapBookToBookDTO(libraryRepository.borrowBook(bookToBorrow.getBookToBorrowId(), bookToBorrow.getReaderId()), null)).filter(Objects::nonNull).toList());
    }

    public BooksResponse returnBook(BookToReturnDTO bookToReturn) {
        return new BooksResponse(Stream.of(mapBookToBookDTO(libraryRepository.returnBook(bookToReturn.getBookToReturnId()), null)).filter(Objects::nonNull).toList());
    }

    private BooksResponse mapBooksToResponse(List<Book> books) {
        return new BooksResponse(books.stream().map((Book book) -> mapBookToBookDTO(book, null)).toList());
    }

    private ReadersResponse mapReadersToResponse(List<Reader> readers) {
        return new ReadersResponse(readers.stream().map(this::mapReaderToReaderDTO).toList());
    }

    private ReaderDTO mapReaderToReaderDTO(Reader reader) {
        if (reader == null) return null;
        return new ReaderDTO(reader.getId(), reader.getFirstName(), reader.getLastName(), reader.getBookIdList());
    }

    private BookDTO mapBookToBookDTO(Book book, Description description) {
        if (book == null) return null;
        DescriptionDTO descriptionDTO = description != null ? new DescriptionDTO(description.getShortDescription(), description.getLongDescription()) : null;
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName(), book.getInStock(), book.getReaderId(),
                descriptionDTO);
    }
}
