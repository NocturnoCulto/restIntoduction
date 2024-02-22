package pl.umk.allegroworkshop.restIntroduction.api.v1;

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

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
public class LibraryApi {
    private final LibraryProvider libraryProvider;

    public LibraryApi(LibraryProvider libraryProvider) {
        this.libraryProvider = libraryProvider;
    }

    @GetMapping(value = "/books", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksResponse> getAllBooks() {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.getAllBooks());
    }

    @GetMapping(value = "/book/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksResponse> getBooksById(@PathVariable Integer id) {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.getBookById(id));
    }

    @GetMapping(value = "/readers", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReadersResponse> getAllReaders() {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.getAllReaders());
    }

    @GetMapping(value = "/reader", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReadersResponse> getReaderById(@RequestParam("id") Integer id,
                                                         @RequestParam(value = "foo", required = false) String foo) {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.getReaderById(id));
    }

    @PutMapping(value = "/book", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AddedBookDTO> addNewBookToLibrary(@RequestBody BookToAddDTO bookToAdd) {
        return ResponseEntity.status(201).body(libraryProvider.addNewBook(bookToAdd));
    }

    public ResponseEntity<RemovedBookDTO> removeBookFromLibrary() {
        return ResponseEntity
                .status(200)
                .body(null);
    }

    public ResponseEntity<BooksResponse> borrowBook() {
        return ResponseEntity
                .status(200)
                .body(null);
    }

    public ResponseEntity<BooksResponse> returnBook() {
        return ResponseEntity
                .status(200)
                .body(null);
    }
}
