package by.vlad.tasktracker.UserService.util;

public enum ErrorType {
    RESOURCE_NOT_FOUND("Resource Not Found"),
    NOT_UNIQUE_VALUE("Not Unique Value"),
    VALIDATION_ERROR("Validation Error");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
