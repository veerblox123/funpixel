package com.farepixel.game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final GameRegistry registry;

    public PlayerJoinListener(GameRegistry registry) {
        this.registry = registry;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        registry.sendPlayerTo(event.getPlayer(), GameModeType.LOBBY);
    }
}
