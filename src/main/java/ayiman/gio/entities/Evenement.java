package ayiman.gio.entities;

import java.time.LocalDateTime;

import ayiman.gio.core.enums.EvenementStatus;
import ayiman.gio.core.enums.TypeEvenement;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_EVENEMENT")
public class Evenement extends BaseEntity {
    @Column(name = "DESCRIPTION")
    public String description;

    @Column(name = "DATE", nullable = false)
    public LocalDateTime date;

    @Column(name = "LIEU")
    public String lieu;

    @Column(name = "TICKET_MAX", nullable = false)
    public Long ticketMax;

    @Column(name = "BILLET_VENDU")
    public Long ticketSaled;

    @Column(name = "PRIX_BILLET", nullable = false)
    public Double ticketPrice;

    @Column(name = "STATUT")
    @Enumerated(EnumType.STRING)
    public EvenementStatus status;

    @Column(name = "TYPE_EVENEMENT")
    @Enumerated(EnumType.STRING)
    public TypeEvenement typeEvenement;

    @Lob
    @Column(name = "IMAGE_DATA")
    public byte[] affiche;

    @ManyToOne
    @JoinColumn(name = "ORGANISATEUR_ID", nullable = false)
    public Organisation organisateur;
}
