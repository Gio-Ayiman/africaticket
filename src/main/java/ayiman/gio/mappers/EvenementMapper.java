package ayiman.gio.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ayiman.gio.entities.Evenement;
import ayiman.gio.entities.Utilisateur;
import ayiman.gio.entities.dtos.EvenementDto;
import ayiman.gio.mappers.interfaces.FromCodeToId;
import ayiman.gio.mappers.interfaces.ToEntityCreate;
import ayiman.gio.mappers.interfaces.ToEntityUpdate;

@Mapper(config = ConfigMapper.class, uses = {ReferenceMapper.class, OrganisationMapper.class})
public interface EvenementMapper {
    public Evenement mapEntity(Long id);

    @ToEntityCreate
    @Mapping(source = "eventDto.desc", target = "description")
    @Mapping(source = "eventDto.time", target = "date")
    @Mapping(source = "eventDto.place", target = "lieu")
    @Mapping(source = "eventDto.billetMax", target = "ticketMax")
    @Mapping(source = "eventDto.eventType", target = "typeEvenement")
    @Mapping(source = "eventDto.createdById", target = "organisateur")
    @Mapping(target = "status", expression = "java(ayiman.gio.core.enums.EvenementStatus.CREE_NON_OUVERT)")
    public Evenement createEvenement(EvenementDto eventDto, Utilisateur user);

    @ToEntityUpdate
    @Mapping(source = "eventDto.desc", target = "description")
    @Mapping(source = "eventDto.time", target = "date")
    @Mapping(source = "eventDto.place", target = "lieu")
    @Mapping(source = "eventDto.billetMax", target = "ticketMax")
    @Mapping(source = "eventDto.eventType", target = "typeEvenement")
    @Mapping(source = "eventDto.createdById", target = "organisateur")
    public Evenement updatEvenement(EvenementDto eventDto, Utilisateur user, @MappingTarget Evenement evenement);

    @Mapping(source = "desc", target = "description")
    @Mapping(source = "time", target = "date")
    @Mapping(source = "place", target = "lieu")
    @Mapping(source = "billetMax", target = "ticketMax")
    @Mapping(source = "eventType", target = "typeEvenement")
    @Mapping(source = "createdById", target = "organisateur")
    @FromCodeToId
    public Evenement toEntity(EvenementDto eventDto);

    @InheritInverseConfiguration
    @Mapping(source = "organisateur.id", target = "createdById")
    public EvenementDto toDto(Evenement evenement);
}
