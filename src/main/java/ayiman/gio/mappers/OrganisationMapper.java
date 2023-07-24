package ayiman.gio.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ayiman.gio.entities.Organisation;
import ayiman.gio.entities.Utilisateur;
import ayiman.gio.entities.dtos.OrganisationDto;
import ayiman.gio.mappers.interfaces.FromCodeToId;
import ayiman.gio.mappers.interfaces.ToEntityCreate;
import ayiman.gio.mappers.interfaces.ToEntityUpdate;

@Mapper(config = ConfigMapper.class, uses = {ReferenceMapper.class})
public interface OrganisationMapper {
    public Organisation mapEntity(Long id);

    @ToEntityCreate
    @Mapping(source = "organisationDto.name", target = "nom")
    public Organisation createOrganisation(OrganisationDto organisationDto, Utilisateur user);

    @ToEntityUpdate
    @Mapping(source = "organisationDto.name", target = "nom")
    public Organisation updateOrganisation(OrganisationDto organisationDto, Utilisateur user, @MappingTarget Organisation organisationFromDb);

    @FromCodeToId
    @Mapping(source = "name", target = "nom")
    public Organisation toEntity(OrganisationDto organisationDto);

    @InheritInverseConfiguration
    public OrganisationDto toDto(Organisation organisation);
}
