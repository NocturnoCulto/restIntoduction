package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BooksResponse;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.ReadersResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
public class LibraryApi {
    private final ResponseProvider responseProvider;

    public LibraryApi(ResponseProvider responseProvider) {
        this.responseProvider = responseProvider;
    }

    @GetMapping(value = "/getBooks", produces = APPLICATION_JSON_VALUE)
    public BooksResponse getAllBooks() {
        return responseProvider.getAllBooks();
    }

    @GetMapping(value = "/getBooks/{id}", produces = APPLICATION_JSON_VALUE)
    public BooksResponse getBooksById(@PathVariable Integer id) {
        return responseProvider.getBookById(id);
    }

    @GetMapping(value = "/getReaders", produces = APPLICATION_JSON_VALUE)
    public ReadersResponse getAllReaders() {
        return responseProvider.getAllReaders();
    }

    @GetMapping(value = "/getReaders/{id}", produces = APPLICATION_JSON_VALUE)
    public ReadersResponse getReaderById(@PathVariable Integer id) {
        return responseProvider.getReaderById(id);
    }



}
