package ayiman.gio.entities;

import ayiman.gio.core.enums.PaymentMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_PERSONNE_MORAL")
public class Organisation extends BaseEntity {

    @Column(name = "NOM", length = 70, nullable = false)
    public String nom; 

    @Column(name = "ADRESSE", length = 50, nullable = false)
    public String adresse;

    @Column(name = "TELEPHONE", length = 20, unique=true, nullable = false)
    public String telephone;

    @Column(name = "NIF")
    public String nif;

    @Column(name = "RCCM")
    public String rccm;

    @Column(name = "ACTIF")
    public boolean isActive = true;

    @Column(name = "MODE_PAIEMENT", nullable = false)
    @Enumerated(EnumType.STRING)
    public PaymentMode paymentMode;
}
