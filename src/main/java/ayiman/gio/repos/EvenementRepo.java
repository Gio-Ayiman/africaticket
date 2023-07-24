package ayiman.gio.repos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ayiman.gio.entities.Evenement;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EvenementRepo implements PanacheRepository<Evenement> {
    public Optional<Evenement> findByUUIDOptional(UUID guid) {
        return find("guid", guid).singleResultOptional();
    }

    public Boolean deleteByUUID(UUID uuid) {
        return delete("guid", uuid) == 1;
    }

    public List<Evenement> findAllByOrganisation(Long organisationId) {
        return list("organisateur.id", organisationId);
    }
}
