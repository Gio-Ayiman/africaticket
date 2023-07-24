package ayiman.gio.repos;

import java.util.Optional;
import java.util.UUID;

import ayiman.gio.entities.Organisation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrganisationRepo implements PanacheRepository<Organisation> {
    public Optional<Organisation> findByUUIDOptional(UUID guid) {
        return find("guid", guid).singleResultOptional();
    }

    public Boolean deleteByUUID(UUID uuid) {
        return delete("guid", uuid) == 1;
    } 
}
