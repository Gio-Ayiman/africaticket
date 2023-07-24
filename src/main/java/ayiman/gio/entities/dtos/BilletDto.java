package ayiman.gio.entities.dtos;

import jakarta.validation.constraints.NotNull;

public class BilletDto extends BaseEntityDto {
    @NotNull(message = "Veuillez entrer le prix du billet")
    public Double price;

    public String lien;

    public byte[] image;
    
    @NotNull(message = "Veuillez spécifier l'évènement du billet")
    public Long eventId;
}
