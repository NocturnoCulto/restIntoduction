package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReadersResponse {
    private final List<ReaderDTO> readers;

    @JsonCreator
    public ReadersResponse(@JsonProperty("readers") List<ReaderDTO> readers) {
        this.readers = readers;
    }

    public List<ReaderDTO> getReaders() {
        return readers;
    }
}
