package pl.umk.allegroworkshop.restIntroduction.api.v1;

import org.springframework.stereotype.Component;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.AddedBookDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToAddDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToRemoveDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.RemovedBookDTO;
import pl.umk.allegroworkshop.restIntroduction.domain.libraryService.LibraryService;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Book;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.BookToAdd;

@Component
public class RequestHandler {
    private final LibraryService libraryService;

    public RequestHandler(LibraryService libraryService) {
        this.libraryService = libraryService;
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
}
