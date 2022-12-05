package pl.umk.allegroworkshop.restIntroduction.api.v2;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.allegroworkshop.restIntroduction.api.v1.LibraryProvider;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BooksResponse;

import java.util.Objects;

@RestController()
public class LibraryApiV2 {
    private final LibraryProvider libraryProvider;
    static final String apiVersionAcceptV2 = "application/v2+json";

    public LibraryApiV2(LibraryProvider libraryProvider) {
        this.libraryProvider = libraryProvider;
    }

    @GetMapping(value = "/getBooks", produces = apiVersionAcceptV2)
    public ResponseEntity<BooksResponse> getAllBooks(@RequestHeader HttpHeaders headers) {
        if (!Objects.equals(headers.getFirst("accept"), apiVersionAcceptV2)) return ResponseEntity.status(406).body(null);

        return ResponseEntity
                .status(200)
                .body(libraryProvider.getAllBooks());
    }
}