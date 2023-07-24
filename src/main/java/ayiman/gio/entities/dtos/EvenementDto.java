package ayiman.gio.entities.dtos;

import java.time.LocalDateTime;

import ayiman.gio.core.enums.TypeEvenement;
import jakarta.validation.constraints.NotNull;

public class EvenementDto extends BaseEntityDto {
    public String desc;

    public byte[] affiche;

    // @NotNull(message = "Veuillez spécifier la date de l'évènement")
    public LocalDateTime time = LocalDateTime.now(); 

    @NotNull(message = "Veuillez spécifier le nombre de place disponible pour cet évènement")
    public String place;

    @NotNull(message = "Veuillez spécifier le nombre de billet à vendre pour cet évènement")
    public Long billetMax;

    @NotNull(message = "Veuillez spécifier le type d'évènement")
    public TypeEvenement eventType;

    @NotNull(message = "Veuillez préciser le prix du billet")
    public Double ticketPrice;

    @NotNull(message = "Veuillez spécifier l'organisateur de l'évènement")
    public Long createdById;
}
