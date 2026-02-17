package com.farepixel.game.duels;

import com.farepixel.FarePixelPlugin;
import com.farepixel.game.GameModeHandler;
import com.farepixel.game.GameModeType;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class DuelsHandler implements GameModeHandler {

    private final Queue<UUID> waitingPlayers = new ArrayDeque<>();
    private final Set<UUID> activePlayers = new HashSet<>();

    public DuelsHandler(FarePixelPlugin plugin) {
    }

    @Override
    public GameModeType getType() {
        return GameModeType.DUELS;
    }

    @Override
    public void join(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD), new ItemStack(Material.COOKED_BEEF, 16));

        UUID next = waitingPlayers.poll();
        if (next != null && !next.equals(player.getUniqueId())) {
            Player opponent = Bukkit.getPlayer(next);
            if (opponent != null && opponent.isOnline()) {
                activePlayers.add(player.getUniqueId());
                activePlayers.add(opponent.getUniqueId());
                player.sendMessage(ChatColor.RED + "Duel started against " + opponent.getName() + "!");
                opponent.sendMessage(ChatColor.RED + "Duel started against " + player.getName() + "!");
                return;
            }
        }

        waitingPlayers.add(player.getUniqueId());
        player.sendMessage(ChatColor.YELLOW + "Waiting for an opponent in Duels...");
    }

    @Override
    public void leave(Player player) {
        waitingPlayers.remove(player.getUniqueId());
        activePlayers.remove(player.getUniqueId());
    }
}
