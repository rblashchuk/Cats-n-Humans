package ru.blashchuk;

import jakarta.persistence.Embeddable;

public enum Color {
    RED,
    GINGER,
    BROWN,
    BLUE,
    GREY,
    BLACK,
    WHITE,
    FAWN;

    @Override
    public String toString() {
        return switch (this) {
            case RED -> "Red";
            case GINGER -> "Ginger";
            case BROWN -> "Brown";
            case BLUE -> "Blue";
            case GREY -> "Grey";
            case BLACK -> "Black";
            case WHITE -> "White";
            case FAWN -> "Fawn";
            default -> throw new IllegalArgumentException();
        };
    }
}

