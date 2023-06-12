package ua.foxminded.bootstrap.models.utils;

public enum Role {
    STUDENT ("Student"),

    TEACHER ("Teacher"),

    ADMIN("Admin"),

    STAFF("Staff");

    private String role;

    private Role (String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
