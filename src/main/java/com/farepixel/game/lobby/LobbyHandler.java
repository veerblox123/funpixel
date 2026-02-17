package com.farepixel.game.lobby;

import com.farepixel.FarePixelPlugin;
import com.farepixel.game.GameModeHandler;
import com.farepixel.game.GameModeType;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LobbyHandler implements GameModeHandler {

    private final FarePixelPlugin plugin;

    public LobbyHandler(FarePixelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public GameModeType getType() {
        return GameModeType.LOBBY;
    }

    @Override
    public void join(Player player) {
        Location spawn = player.getWorld().getSpawnLocation();
        player.teleport(spawn);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);
        player.sendMessage(ChatColor.GOLD + "Welcome to the FarePixel Lobby!");
    }

    @Override
    public void leave(Player player) {
        // No cleanup needed for lobby transitions right now.
    }
}
