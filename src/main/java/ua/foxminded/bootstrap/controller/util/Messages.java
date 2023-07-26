package ua.foxminded.bootstrap.controller.util;

public enum Messages {
    ERROR("errorMessage"),
    SUCCESS_MESSAGE("successMessage"),
    SUCCESS("Success!");

    private final String value;

    Messages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

