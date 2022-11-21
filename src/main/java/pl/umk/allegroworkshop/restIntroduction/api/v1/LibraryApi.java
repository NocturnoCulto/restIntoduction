package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.AddedBookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToAddDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToRemoveDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.RemovedBookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BooksResponse;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.ReadersResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
public class LibraryApi {
    private final ResponseProvider responseProvider;
    private final RequestHandler requestHandler;

    public LibraryApi(ResponseProvider responseProvider, RequestHandler requestHandler) {
        this.responseProvider = responseProvider;
        this.requestHandler = requestHandler;
    }

    @GetMapping(value = "/getBooks", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksResponse> getAllBooks() {
        return ResponseEntity
                .status(200)
                .body(responseProvider.getAllBooks());
    }

    @GetMapping(value = "/getBooks/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksResponse> getBooksById(@PathVariable Integer id) {
        return ResponseEntity
                .status(200)
                .body(responseProvider.getBookById(id));
    }

    @GetMapping(value = "/getReaders", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReadersResponse> getAllReaders() {
        return ResponseEntity
                .status(200)
                .body(responseProvider.getAllReaders());
    }

    @GetMapping(value = "/getReaders/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReadersResponse> getReaderById(@PathVariable Integer id) {
        return ResponseEntity
                .status(200)
                .body(responseProvider.getReaderById(id));
    }

    @PutMapping(value = "/addBook", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AddedBookDTO> addNewBookToLibrary(@RequestBody BookToAddDTO bookToAddDTO) {
        return ResponseEntity.status(201).body(requestHandler.addNewBook(bookToAddDTO));
    }

    @DeleteMapping(value = "/removeBook", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RemovedBookDTO> removeBookFromLibrary(@RequestBody BookToRemoveDTO bookToRemoveDTO) {
        return ResponseEntity
                .status(200)
                .body(requestHandler.removeBook(bookToRemoveDTO));
    }
}
