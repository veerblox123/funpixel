package com.farepixel.commands;

import com.farepixel.game.GameModeHandler;
import com.farepixel.game.GameModeType;
import com.farepixel.game.GameRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class FarePixelCommand implements CommandExecutor, TabCompleter {

    private final GameRegistry registry;

    public FarePixelCommand(GameRegistry registry) {
        this.registry = registry;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /farepixel <lobby|duels|bedwars>");
            return true;
        }

        GameModeType mode = GameModeType.fromString(args[0]);
        if (mode == null) {
            sender.sendMessage(ChatColor.RED + "Unknown mode. Available: lobby, duels, bedwars");
            return true;
        }

        if (registry.sendPlayerTo(player, mode)) {
            sender.sendMessage(ChatColor.GREEN + "Sent to " + ChatColor.AQUA + mode.name().toLowerCase() + ChatColor.GREEN + ".");
        } else {
            sender.sendMessage(ChatColor.RED + "Mode is not available right now.");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            String prefix = args[0].toLowerCase();
            return registry.getHandlers().stream()
                    .map(GameModeHandler::getType)
                    .map(type -> type.name().toLowerCase())
                    .filter(option -> option.startsWith(prefix))
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
