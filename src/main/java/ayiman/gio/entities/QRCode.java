package ayiman.gio.entities;

import ayiman.gio.core.enums.StatutQRCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_QRCODE")
public class QRCode extends BaseEntity{
    @Column(name = "DATA")
    public String data;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    public StatutQRCode statut;

    @Column(name = "ACHETE_PAR", length = 30, nullable = false)
    public String getByPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "BILLET_ID", referencedColumnName = "id", nullable = false)
    public Billet billet;
}
