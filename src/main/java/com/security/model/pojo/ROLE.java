package com.security.model.pojo;

public enum ROLE {

    USER("USER"),
    ADMIN("ADMIN");

    private final String text;

    ROLE(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
