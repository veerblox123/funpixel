package com.farepixel;

import com.farepixel.commands.FarePixelCommand;
import com.farepixel.game.GameRegistry;
import com.farepixel.game.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class FarePixelPlugin extends JavaPlugin {

    private GameRegistry gameRegistry;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.gameRegistry = new GameRegistry(this);
        this.gameRegistry.registerDefaults();

        FarePixelCommand command = new FarePixelCommand(gameRegistry);
        if (getCommand("farepixel") != null) {
            getCommand("farepixel").setExecutor(command);
            getCommand("farepixel").setTabCompleter(command);
        }

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(gameRegistry), this);
        getLogger().info("FarePixel enabled with Lobby, Duels, and BedWars game modes.");
    }

    @Override
    public void onDisable() {
        getLogger().info("FarePixel disabled.");
    }
}
