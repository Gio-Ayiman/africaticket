package ayiman.gio.core.enums;

public enum ErrorEnum {
    NULL_PARAMETER(1000, "NullParameterException", "The parameter passed is null"),
    ENTITY_NOT_FOUND(1001, "EntityNotFoundException", "The entity passed is not found"),
    ORGANISATION_NOT_ACTIVE(1002, "OrganisationNotActiveException", "The organisation is not active"),

    // OTP
    OTP_NOT_FOUND(1100, "OTPNotFoundException", "No OTP found"),
    OTP_EXPIRED(1101, "OTPExpired", "This OTP has expired"),

    // POVIDER
    PROVIDER_ERROR(1200, "ProviderException", "An error occured while sending the message");


    private final int code;
    private final String type;
    private final String message;

    ErrorEnum(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
