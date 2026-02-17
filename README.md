# FarePixel Plugin

FarePixel is a Paper/Spigot-compatible Minecraft plugin that provides three core server experiences:

- **Lobby** mode for player hub/spawn.
- **Duels** mode with lightweight 1v1 matchmaking.
- **BedWars** mode with an 8-player queue and auto-start behavior.

## Build

```bash
mvn clean package
```

The plugin jar will be generated in `target/farepixel-1.0.0.jar`.

## Commands

- `/farepixel lobby`
- `/farepixel duels`
- `/farepixel bedwars`

## Notes

This is a starter implementation intended for extension (arenas, teams, scoreboards, kits, persistence, etc.).
