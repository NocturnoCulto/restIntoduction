package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.BooksResponse;
import pl.umk.allegroworkshop.restIntroduction.domain.booksService.BooksService;
import pl.umk.allegroworkshop.restIntroduction.domain.model.Book;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ResponseProvider {
    private final BooksService booksService;

    public ResponseProvider(BooksService booksService) {
        this.booksService = booksService;
    }

    public BooksResponse getAllBooks() {
        return mapBooksToResponse(booksService.getAllBooks());
    }

    public BooksResponse getBookById(Integer id) {
        return new BooksResponse(Collections.singletonList(mapBookToBookDTO(booksService.getBookById(id))));
    }

    private BooksResponse mapBooksToResponse(List<Book> books) {
        return new BooksResponse(books.stream().map(this::mapBookToBookDTO).toList());
    }

    private BookDTO mapBookToBookDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getAuthor().getLastName(), book.getInStock());
    }

}
