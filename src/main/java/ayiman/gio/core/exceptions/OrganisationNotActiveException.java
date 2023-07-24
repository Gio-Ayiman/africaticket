package ayiman.gio.core.exceptions;

import ayiman.gio.core.enums.ErrorEnum;

public class OrganisationNotActiveException extends AfricaTicketException {
    private static final ErrorEnum error = ErrorEnum.ORGANISATION_NOT_ACTIVE;
    
    public OrganisationNotActiveException() {
        super(error.getCode(), error.getType(), error.getMessage(), null);
    }
}
