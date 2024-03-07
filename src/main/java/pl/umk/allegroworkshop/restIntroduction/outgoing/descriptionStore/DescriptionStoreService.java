package pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore;

import org.springframework.stereotype.Service;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Description;
import pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore.model.ExternalDescription;

@Service
public class DescriptionStoreService {
    private final DescriptionStoreClient descriptionStoreClient;

    public DescriptionStoreService(DescriptionStoreClient descriptionStoreClient) {
        this.descriptionStoreClient = descriptionStoreClient;
    }

    public Description getDescriptionById(Integer id) {
        ExternalDescription externalDescription = descriptionStoreClient.findDescriptionById(id.toString());
        if (externalDescription == null) return null;
        return new Description(externalDescription.shortText(), externalDescription.longText());
    }
}
