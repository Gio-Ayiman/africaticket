package ayiman.gio.repos;

import java.util.Optional;
import java.util.UUID;

import ayiman.gio.entities.Billet;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BilletRepo implements PanacheRepository<Billet>{
    public Optional<Billet> findByUUIDOptional(UUID uuid) {
        return find("guid", uuid).singleResultOptional();
    }

    public Optional<Billet> findByEvent(Long eventId) {
        return find("evenement.id", eventId).singleResultOptional();
    }
}
