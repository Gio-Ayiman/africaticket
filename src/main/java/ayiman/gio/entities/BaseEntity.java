package ayiman.gio.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity extends PanacheEntity{
    @Column(name = "GUID")
    public UUID guid = UUID.randomUUID();

    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    public LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    @CreationTimestamp
    public LocalDateTime updatedAt;

    @Column(name = "CREATED_AT_BY")
    public String createdBy;

    @Column(name = "UPDATED_BY")
    public String updatedBy;
}
