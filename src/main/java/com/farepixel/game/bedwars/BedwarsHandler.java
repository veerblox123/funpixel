package com.farepixel.game.bedwars;

import com.farepixel.FarePixelPlugin;
import com.farepixel.game.GameModeHandler;
import com.farepixel.game.GameModeType;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BedwarsHandler implements GameModeHandler {

    private final Queue<UUID> queue = new ArrayDeque<>();
    private static final int TEAM_SIZE = 4;

    public BedwarsHandler(FarePixelPlugin plugin) {
    }

    @Override
    public GameModeType getType() {
        return GameModeType.BEDWARS;
    }

    @Override
    public void join(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
        queue.offer(player.getUniqueId());

        player.sendMessage(ChatColor.AQUA + "Joined BedWars queue (" + queue.size() + "/" + (TEAM_SIZE * 2) + ")");

        if (queue.size() >= TEAM_SIZE * 2) {
            startMatch();
        }
    }

    private void startMatch() {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < TEAM_SIZE * 2; i++) {
            UUID id = queue.poll();
            if (id == null) {
                break;
            }

            Player player = Bukkit.getPlayer(id);
            if (player != null && player.isOnline()) {
                names.append(player.getName()).append(i < TEAM_SIZE * 2 - 1 ? ", " : "");
                player.sendMessage(ChatColor.GREEN + "BedWars match starting!");
            }
        }

        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "FarePixel BedWars started with players: " + names);
    }

    @Override
    public void leave(Player player) {
        queue.remove(player.getUniqueId());
    }
}
