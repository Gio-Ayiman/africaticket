package ayiman.gio.services;

import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ayiman.gio.entities.Billet;
import ayiman.gio.entities.Evenement;
import ayiman.gio.entities.InfoUser;
import ayiman.gio.entities.dtos.BilletDto;
import ayiman.gio.mappers.BilletMapper;
import ayiman.gio.repos.BilletRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BilletService {
    @ConfigProperty(name = "billeterie.link")
    String link;
    
    @Inject
    BilletRepo billetRepository;

    @Inject 
    BilletMapper billetMapper;

    @Inject
    EvenementService evenementService;

    @Inject
    InfoUser user;

    public BilletDto getOne(UUID uuid) {
        if(uuid == null) return null;

        Billet billet = billetRepository.findByUUIDOptional(uuid).orElseThrow(null);

        return billetMapper.toDto(billet);
    }

    public BilletDto createBilletFromEvent(Evenement evenement) {
        Billet billet = billetMapper.createBillet(evenement, user.userInfo());
        billetRepository.persist(billet);
        return billetMapper.toDto(billet);
    }
    
    public BilletDto updaBilletFromEvent(Evenement evenement) {
        Evenement event = evenementService.getEntity(evenement.id);
        Billet billetFromDb = billetRepository.findByEvent(event.id).orElseThrow();
        Billet billetUpdated = billetMapper.updateBillet(evenement, user.userInfo(), billetFromDb);
        return billetMapper.toDto(billetUpdated);
    }

    public boolean delete(Long id) {
        return billetRepository.deleteById(id);
    }
}
