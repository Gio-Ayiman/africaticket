package ayiman.gio.entities;

import jakarta.persistence.Column;

public abstract class AbstractPersonne extends BaseEntity {
    @Column(name = "NOM")
    public String nom;

    @Column(name = "EMAIL")
    public String email;     
}
