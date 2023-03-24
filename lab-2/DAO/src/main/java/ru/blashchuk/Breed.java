package ru.blashchuk;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

public enum Breed {
    ABYSSINIAN,
    RUSSIAN_BLUE,
    NORWEGIAN_FOREST,
    RAGDOLL,
    PERSIAN;

    @Override
    public String toString() {
        return switch (this) {
            case ABYSSINIAN -> "Abyssinian";
            case RUSSIAN_BLUE -> "Russian Blue";
            case NORWEGIAN_FOREST -> "Norwegian Forest";
            case RAGDOLL -> "Ragdoll";
            case PERSIAN -> "Persian";
            default -> throw new IllegalArgumentException();
        };
    }
}
