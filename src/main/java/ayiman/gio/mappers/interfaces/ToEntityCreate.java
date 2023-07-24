package ayiman.gio.mappers.interfaces;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "id", ignore = true)
@Mapping(target = "guid", ignore = true)
@Mapping(source = "user.username", target = "createdBy")
@Mapping(source = "user.username", target = "updatedBy")
public @interface ToEntityCreate {
}