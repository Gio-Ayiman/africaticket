package ayiman.gio.core.exceptions;

import ayiman.gio.core.enums.ErrorEnum;

public class NullParameterException extends AfricaTicketException {
    private static final ErrorEnum error = ErrorEnum.NULL_PARAMETER;

    public NullParameterException() {
        super(error.getCode(), error.getType(), error.getMessage(), null);
    }
    
}
