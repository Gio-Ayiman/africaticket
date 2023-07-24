package ayiman.gio.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ayiman.gio.core.enums.EvenementStatus;
import ayiman.gio.core.enums.OpenCloture;
import ayiman.gio.core.exceptions.EntityNotFoundException;
import ayiman.gio.core.exceptions.OrganisationNotActiveException;
import ayiman.gio.core.helpers.Const;
import ayiman.gio.entities.Evenement;
import ayiman.gio.entities.InfoUser;
import ayiman.gio.entities.Organisation;
import ayiman.gio.entities.dtos.EvenementDto;
import ayiman.gio.entities.dtos.OrganisationDto;
import ayiman.gio.mappers.EvenementMapper;
import ayiman.gio.repos.EvenementRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EvenementService {
    @Inject
    EvenementMapper evenementMapper;

    @Inject
    EvenementRepo evenementRepo;

    @Inject
    BilletService billetService;

    @Inject
    OrganisationService organisationService;

    @Inject
    InfoUser user;

    public List<EvenementDto> findAll() {
        return evenementRepo.findAll()
                .stream()
                .map(evenementMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EvenementDto> findAllByOrganisation(UUID guid) {
        OrganisationDto organisationDto = organisationService.getOne(guid);
        Organisation organisation = organisationService.getEntity(organisationDto.code);

        return evenementRepo.findAllByOrganisation(organisation.id)
                .stream()
                .map(evenementMapper::toDto)
                .collect(Collectors.toList());
    }

    public EvenementDto getOne(UUID guid) {
        Evenement evenement = evenementRepo.findByUUIDOptional(guid).orElseThrow(() -> new EntityNotFoundException(UUID.class));
        return evenementMapper.toDto(evenement);
    }
    
    public Evenement getEntity(Long id) {
        return evenementRepo.findByIdOptional(id).orElseThrow(() -> new EntityNotFoundException(id.getClass()));
    }

    public String openOrCloture(UUID guid, OpenCloture status) {
        Evenement evenement = evenementRepo.findByUUIDOptional(guid).orElseThrow(() -> new EntityNotFoundException(guid.getClass()));
        EvenementDto evenementDto = evenementMapper.toDto(evenement);
        

        if (status == OpenCloture.CLOTURE) {
            evenement.status = EvenementStatus.CLOTURE;
            evenementMapper.updatEvenement(evenementDto, user.userInfo(), evenement);
            return Const.EVENT_CLOSED;
        }


        evenement.status = EvenementStatus.OUVERT;
        evenementMapper.updatEvenement(evenementDto, user.userInfo(), evenement);
        return Const.EVENT_OPENED;
    }
    
    @Transactional
    public EvenementDto create(EvenementDto evenementDto) {
        Evenement evenement = evenementMapper.createEvenement(evenementDto, user.userInfo());
        Organisation organisation = organisationService.getEntity(evenement.organisateur.id);

        if(!organisation.isActive) throw new OrganisationNotActiveException();

        evenementRepo.persist(evenement);

        billetService.createBilletFromEvent(evenement);

        return evenementMapper.toDto(evenement);
    }

    @Transactional
    public EvenementDto update(EvenementDto evenementDto) {
        Evenement eventFromDb = getEntity(evenementDto.code);
        Evenement evenementUpdated = evenementMapper.updatEvenement(evenementDto, user.userInfo(), eventFromDb);

        billetService.updaBilletFromEvent(evenementUpdated);
        return evenementMapper.toDto(evenementUpdated);
    }

    @Transactional
    public Boolean delete(UUID id) {
        return evenementRepo.deleteByUUID(id);
    }

}