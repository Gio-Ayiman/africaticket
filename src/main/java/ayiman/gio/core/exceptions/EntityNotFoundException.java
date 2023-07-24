package ayiman.gio.core.exceptions;

import ayiman.gio.core.enums.ErrorEnum;

public class EntityNotFoundException extends AfricaTicketException {
    private static final ErrorEnum error = ErrorEnum.ENTITY_NOT_FOUND;

    public EntityNotFoundException(Class<?> entity) {
        super(error.getCode(), error.getType(), error.getMessage().concat(" : " + entity), null);
    }
}
