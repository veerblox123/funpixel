package com.farepixel.game;

import com.farepixel.FarePixelPlugin;
import com.farepixel.game.bedwars.BedwarsHandler;
import com.farepixel.game.duels.DuelsHandler;
import com.farepixel.game.lobby.LobbyHandler;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;

public class GameRegistry {

    private final FarePixelPlugin plugin;
    private final Map<GameModeType, GameModeHandler> handlers = new EnumMap<>(GameModeType.class);
    private final Map<UUID, GameModeType> playerModes = new java.util.HashMap<>();

    public GameRegistry(FarePixelPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerDefaults() {
        register(new LobbyHandler(plugin));
        register(new DuelsHandler(plugin));
        register(new BedwarsHandler(plugin));
    }

    public void register(GameModeHandler handler) {
        handlers.put(handler.getType(), handler);
    }

    public boolean sendPlayerTo(Player player, GameModeType type) {
        GameModeHandler target = handlers.get(type);
        if (target == null) {
            return false;
        }

        GameModeType previous = playerModes.get(player.getUniqueId());
        if (previous != null) {
            GameModeHandler previousHandler = handlers.get(previous);
            if (previousHandler != null) {
                previousHandler.leave(player);
            }
        }

        target.join(player);
        playerModes.put(player.getUniqueId(), type);
        return true;
    }

    public Collection<GameModeHandler> getHandlers() {
        return handlers.values();
    }
}
