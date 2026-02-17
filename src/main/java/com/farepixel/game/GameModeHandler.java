package com.farepixel.game;

import org.bukkit.entity.Player;

public interface GameModeHandler {
    GameModeType getType();

    void join(Player player);

    void leave(Player player);

    default String getDisplayName() {
        String name = getType().name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
