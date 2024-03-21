package pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore;

import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.stereotype.Service;
import pl.umk.allegroworkshop.restIntroduction.domain.model.books.Description;
import pl.umk.allegroworkshop.restIntroduction.outgoing.descriptionStore.model.ExternalDescription;

@Service
public class DescriptionStoreService {
    private final LoadingCache<String, ExternalDescription> descriptionStoreCache;


    public DescriptionStoreService(LoadingCache<String, ExternalDescription> descriptionStoreCache) {
        this.descriptionStoreCache = descriptionStoreCache;
    }

    public Description getDescriptionById(Integer id) {
        ExternalDescription externalDescription = descriptionStoreCache.get(id.toString());
        if (externalDescription == null) return null;
        return new Description(externalDescription.shortText(), externalDescription.longText());
    }
}