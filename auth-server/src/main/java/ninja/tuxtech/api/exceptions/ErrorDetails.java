package ninja.tuxtech.api.exceptions;

import java.util.Date;
import java.util.Optional;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Optional<Date> getTimestamp() {
        return Optional.ofNullable(timestamp);
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Optional<String> getDetails() {
        return Optional.ofNullable(details);
    }

    public void setDetails(String details) {
        this.details = details;
    }
}