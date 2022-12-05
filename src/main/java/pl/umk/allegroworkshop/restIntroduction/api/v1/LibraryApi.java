package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToAddDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToBorrowDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToRemoveDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToReturnDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.AddedBookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BooksResponse;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.ReadersResponse;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.RemovedBookDTO;


import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
public class LibraryApi {
    private final LibraryProvider libraryProvider;
    static final String apiVersionAccept = "application/v1+json";

    public LibraryApi(LibraryProvider libraryProvider) {
        this.libraryProvider = libraryProvider;
    }

    @GetMapping(value = "/getBooks", produces = apiVersionAccept)
    public ResponseEntity<BooksResponse> getAllBooks(@RequestHeader HttpHeaders headers) {
        if (!Objects.equals(headers.getFirst("accept"), apiVersionAccept)) return ResponseEntity.status(406).body(null);
        return ResponseEntity
                .status(200)
                .body(libraryProvider.getAllBooks());
    }

    @GetMapping(value = "/getBook/{id}", produces = apiVersionAccept)
    public ResponseEntity<BooksResponse> getBooksById(@PathVariable Integer id) {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.getBookById(id));
    }

    @GetMapping(value = "/getReaders", produces = apiVersionAccept)
    public ResponseEntity<ReadersResponse> getAllReaders() {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.getAllReaders());
    }

    @GetMapping(value = "/getReader", produces = apiVersionAccept)
    public ResponseEntity<ReadersResponse> getReaderById(@RequestParam(value = "id") Integer id) {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.getReaderById(id));
    }

    @PutMapping(value = "/addBook", consumes = APPLICATION_JSON_VALUE, produces = apiVersionAccept)
    public ResponseEntity<AddedBookDTO> addNewBookToLibrary(@RequestBody BookToAddDTO bookToAddDTO) {
        return ResponseEntity.status(201).body(libraryProvider.addNewBook(bookToAddDTO));
    }

    @DeleteMapping(value = "/removeBook", consumes = APPLICATION_JSON_VALUE, produces = apiVersionAccept)
    public ResponseEntity<RemovedBookDTO> removeBookFromLibrary(@RequestBody BookToRemoveDTO bookToRemoveDTO) {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.removeBook(bookToRemoveDTO));
    }

    @PostMapping(value = "/borrowBook", consumes = APPLICATION_JSON_VALUE, produces = apiVersionAccept)
    public ResponseEntity<BooksResponse> borrowBook(@RequestBody BookToBorrowDTO bookToBorrow) {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.borrowBook(bookToBorrow));
    }

    @PostMapping(value = "/returnBook", consumes = APPLICATION_JSON_VALUE, produces = apiVersionAccept)
    public ResponseEntity<BooksResponse> returnBook(@RequestBody BookToReturnDTO bookToReturn) {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.returnBook(bookToReturn));
    }
}
