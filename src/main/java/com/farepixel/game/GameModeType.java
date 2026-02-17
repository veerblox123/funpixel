package com.farepixel.game;

public enum GameModeType {
    LOBBY,
    DUELS,
    BEDWARS;

    public static GameModeType fromString(String value) {
        for (GameModeType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
