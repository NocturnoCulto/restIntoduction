package pl.umk.allegroworkshop.restIntroduction.outgoing.spoonacular.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResults {
    private final List<SearchItem> results;

    @JsonCreator
    public SearchResults(@JsonProperty("results") List<SearchItem> results) {
        this.results = results;
    }

    public List<SearchItem> getResults() {
        return results;
    }
}
