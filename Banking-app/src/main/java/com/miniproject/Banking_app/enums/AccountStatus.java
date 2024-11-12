package com.miniproject.Banking_app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountStatus {
    ACTIVATED,
    SUSPENDED;

    @JsonCreator
    public static AccountStatus fromString(String status) {
        try {
            return AccountStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown value for AccountStatus: " + status);
        }
    }

    @JsonValue
    public String toString() {
        return name();
    }
}
