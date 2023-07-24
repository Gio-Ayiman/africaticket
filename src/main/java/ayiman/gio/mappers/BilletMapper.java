package ayiman.gio.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ayiman.gio.entities.Billet;
import ayiman.gio.entities.Evenement;
import ayiman.gio.entities.Utilisateur;
import ayiman.gio.entities.dtos.BilletDto;
import ayiman.gio.mappers.interfaces.ToEntityCreate;
import ayiman.gio.mappers.interfaces.ToEntityUpdate;

@Mapper(config = ConfigMapper.class, uses = { ReferenceMapper.class, EvenementMapper.class, QRCodeMapper.class })
public interface BilletMapper {

    public Billet mapEntity(Long id);

    @ToEntityCreate
    @Mapping(target = "lien", source = "evenement.guid")
    @Mapping(source = "evenement.ticketPrice", target = "prix")
    @Mapping(source = "evenement", target = "evenement")
    @Mapping(source = "evenement.affiche", target = "image")
    public Billet createBillet(Evenement evenement, Utilisateur user);

    @ToEntityUpdate
    @Mapping(target = "lien", source = "evenement.guid")
    @Mapping(source = "evenement.ticketPrice", target = "prix")
    @Mapping(source = "evenement", target = "evenement")
    @Mapping(source = "evenement.affiche", target = "image")
    public Billet updateBillet(Evenement evenement, Utilisateur user, @MappingTarget Billet billletFromDb);

    @Mapping(source = "billetDto.price", target = "prix")
    @Mapping(source = "billetDto.eventId", target = "evenement")
    public Billet toEntity(BilletDto billetDto);

    @InheritInverseConfiguration
    @Mapping(source = "evenement.id", target = "eventId")
    public BilletDto toDto(Billet billet);

}
