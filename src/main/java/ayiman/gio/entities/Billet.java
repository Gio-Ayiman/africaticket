package ayiman.gio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_BILLET")
public class Billet extends BaseEntity {
    @Column(name = "PRIX")
    public Double prix;

    @Column(name = "LINK")
    public String lien;

    @Lob
    @Column(name = "AFFICHE_DATA")
    public byte[] image;

    @ManyToOne
    @JoinColumn(name = "EVENEMENT_ID", referencedColumnName = "ID")
    public Evenement evenement;
}
