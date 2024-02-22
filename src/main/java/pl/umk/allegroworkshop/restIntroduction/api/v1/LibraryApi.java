package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.AddedBookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BooksResponse;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.ReadersResponse;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.RemovedBookDTO;

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
        return ResponseEntity
                .status(200)
                .body(null);
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
