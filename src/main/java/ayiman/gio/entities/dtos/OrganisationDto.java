package ayiman.gio.entities.dtos;

import ayiman.gio.core.enums.PaymentMode;
import jakarta.validation.constraints.NotNull;

public class OrganisationDto extends BaseEntityDto {

    @NotNull(message = "Veuillez spécifier le nom de l'entreprise")
    public String name;

    public String nif;

    @NotNull(message = "Veuillez spécifier l'adresse")
    public String adresse;

    @NotNull(message = "Veuillez préciser le numéro de téléphone")
    public String telephone;

    public String rccm;

    public boolean isActive;

    @NotNull(message = "Veuillez spécifier le mode de paiement")
    public PaymentMode paymentMode;    

    @Override
    public String toString() {
        return String.format("%s, %s, %s" , name, adresse, telephone);
    }
    
}
