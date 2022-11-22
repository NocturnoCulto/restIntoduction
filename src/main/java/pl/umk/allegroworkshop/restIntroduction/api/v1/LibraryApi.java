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

import javax.websocket.server.PathParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
public class LibraryApi {
    private final LibraryProvider libraryProvider;

    public LibraryApi(LibraryProvider libraryProvider) {
        this.libraryProvider = libraryProvider;
    }

    public ResponseEntity<BooksResponse> getAllBooks() {
        return ResponseEntity
                .status(200)
                .body(null);
    }

    public ResponseEntity<BooksResponse> getBooksById() {
        return ResponseEntity
                .status(200)
                .body(null);
    }

    public ResponseEntity<ReadersResponse> getAllReaders() {
        return ResponseEntity
                .status(200)
                .body(null);
    }

    public ResponseEntity<ReadersResponse> getReaderById() {
        return ResponseEntity
                .status(200)
                .body(null);
    }

    public ResponseEntity<AddedBookDTO> addNewBookToLibrary() {
        return ResponseEntity.status(200).body(null);
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

    @PostMapping(value = "/returnBook", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksResponse> returnBook(@RequestBody BookToReturnDTO bookToReturn) {
        return ResponseEntity
                .status(200)
                .body(libraryProvider.returnBook(bookToReturn));
    }
}
