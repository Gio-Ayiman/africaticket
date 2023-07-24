package ayiman.gio.core.exceptions;

import java.util.Optional;

public class ErrorResponse {
    private int statusCode;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(int code, String error, String message, Optional<String> path) {
        this.statusCode = code;
        this.error = error;
        this.message = message;
        this.path =  path == null ? null : path.get();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int code) {
        this.statusCode = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
