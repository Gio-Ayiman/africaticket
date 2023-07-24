package ayiman.gio.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ayiman.gio.entities.QRCode;
import ayiman.gio.entities.Utilisateur;
import ayiman.gio.entities.dtos.QRCodeDto;
import ayiman.gio.mappers.interfaces.ToEntityCreate;

@Mapper(config = ConfigMapper.class, uses = {ReferenceMapper.class})
public interface QRCodeMapper {
    public QRCode map(Long id);

    @ToEntityCreate
    @Mapping(source = "qrCodeDto.status", target = "statut")
    @Mapping(source = "qrCodeDto.data", target = "data")
    public QRCode createQrCode(QRCodeDto qrCodeDto, Utilisateur user);

    @Mapping(source = "qrCodeDto.status", target = "statut")
    public QRCode toEntity(QRCodeDto qrCodeDto);

    @InheritInverseConfiguration
    public QRCodeDto toDto(QRCode qrCode);
    
}
