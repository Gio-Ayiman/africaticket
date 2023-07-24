package ayiman.gio.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ayiman.gio.core.enums.ActiveStatus;
import ayiman.gio.core.exceptions.EntityNotFoundException;
import ayiman.gio.core.helpers.Const;
import ayiman.gio.entities.InfoUser;
import ayiman.gio.entities.Organisation;
import ayiman.gio.entities.dtos.OrganisationDto;
import ayiman.gio.mappers.OrganisationMapper;
import ayiman.gio.repos.OrganisationRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OrganisationService {

    @Inject
    OrganisationRepo organisationRepo;

    @Inject
    OrganisationMapper organisationMapper;

    @Inject
    InfoUser user;

    public List<OrganisationDto> findAll() {
        return organisationRepo.findAll()
                .stream()
                .map(organisationMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrganisationDto getOne(UUID guid) {
        Organisation organisation = organisationRepo.findByUUIDOptional(guid).orElseThrow(() -> new EntityNotFoundException(guid.getClass()));
        return organisationMapper.toDto(organisation);
    }

    public Organisation getEntity(Long id) {
        return organisationRepo.findByIdOptional(id).orElseThrow();
    }

    @Transactional
    public String activateOrDesactive(UUID guid, ActiveStatus status) {
        Organisation organisation = organisationRepo.findByUUIDOptional(guid).orElseThrow(() -> new EntityNotFoundException(guid.getClass()));

        if (status == ActiveStatus.DESACTIVE) {
            organisation.isActive = false;
            organisationRepo.persist(organisation);
            return Const.ORGANISATION_DESACTIVATED;
        }
        
        organisation.isActive = true;
        organisationRepo.persist(organisation);
        return Const.ORGANISATION_ACTIVATED;
    }

    @Transactional
    public OrganisationDto create(OrganisationDto organisationDto) {
        Organisation organisation = organisationMapper.createOrganisation(organisationDto, user.userInfo());
        organisationRepo.persist(organisation);

        return organisationMapper.toDto(organisation);
    }

    @Transactional
    public OrganisationDto update(OrganisationDto organisationDto) {
        Organisation organisationDb = organisationRepo.findByIdOptional(organisationDto.code).orElseThrow(() -> new EntityNotFoundException(organisationDto.getClass()));

        Organisation organisation = organisationMapper.updateOrganisation(organisationDto, user.userInfo(),
                organisationDb);
        return organisationMapper.toDto(organisation);
    }

    @Transactional
    public Boolean delete(UUID id) {
        return organisationRepo.deleteByUUID(id);
    }

}
