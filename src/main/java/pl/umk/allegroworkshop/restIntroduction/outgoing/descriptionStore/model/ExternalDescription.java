package pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExternalDescription(String id,
                                  String shortText,
                                  String longText) {
    @JsonCreator
    public ExternalDescription(@JsonProperty("id") String id,
                               @JsonProperty("shortText") String shortText,
                               @JsonProperty("longText") String longText) {
        this.id = id;
        this.shortText = shortText;
        this.longText = longText;
    }
}
