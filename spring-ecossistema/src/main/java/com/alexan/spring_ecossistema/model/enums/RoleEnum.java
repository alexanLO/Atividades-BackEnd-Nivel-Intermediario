package com.alexan.spring_ecossistema.model.enums;

public enum RoleEnum {

    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static RoleEnum fromString(String role) {
        for (RoleEnum r : RoleEnum.values()) {
            if (r.role.equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No constant with role " + role + " found");
    }
}
