package ayiman.gio.mappers;

import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;

import ayiman.gio.entities.BaseEntity;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.NotNull;


@ApplicationScoped
public class ReferenceMapper {
    
    @PersistenceContext
    EntityManager em;

    @ObjectFactory
    public <T extends BaseEntity> T resolve(@NotNull final Long id, @TargetType Class<T> entityClass){
        Log.info("=======> ReferenceMapper.resolve => ID: {"+id+"}");
        return em.find(entityClass, id);
    }
}
