package pl.umk.allegroworkshop.restIntroduction.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;

public record DescriptionDTO(String shortDescription, String longDescription) {
    @JsonCreator
    public DescriptionDTO {
    }
}
