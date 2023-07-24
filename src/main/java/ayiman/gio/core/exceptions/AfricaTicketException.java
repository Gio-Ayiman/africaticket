package ayiman.gio.core.exceptions;

import java.util.Optional;

public class AfricaTicketException extends RuntimeException {
    public ErrorResponse errorResponse;

    public AfricaTicketException(int code, String error, String message, Optional<String> path) {
        this.errorResponse = new ErrorResponse(code, error, message, path);
    }

    public AfricaTicketException(ErrorResponse response) {
        this.errorResponse = response;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
