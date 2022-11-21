package pl.umk.allegroworkshop.restIntroduction.domain.model.books;

public class BookToAdd {
    private final String title;
    private final String authorFirstName;
    private final String authorLastName;

    public BookToAdd(String title, String authorFirstName, String authorLastName) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }
}
