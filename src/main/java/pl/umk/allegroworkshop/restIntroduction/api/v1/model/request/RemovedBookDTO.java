package pl.umk.allegroworkshop.restIntroduction.api.v1.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemovedBookDTO {
    private final Integer removedBookId;

    @JsonCreator
    public RemovedBookDTO(@JsonProperty("removedBookId") Integer removedBookId) {
        this.removedBookId = removedBookId;
    }

    public Integer getRemovedBookId() {
        return removedBookId;
    }
}
