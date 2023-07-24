package ayiman.gio.mappers.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.mapstruct.Mapping;

@Retention(RetentionPolicy.CLASS)
@Mapping(source = "code", target = "id")
@Mapping(target = "guid", ignore = true)
public @interface FromCodeToId {
}
