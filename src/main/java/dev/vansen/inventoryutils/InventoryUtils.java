package dev.vansen.inventoryutils;

import dev.vansen.inventoryutils.inventory.InventoryEvents;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Utility class for initializing and managing inventory-related events.
 */
@SuppressWarnings("unused")
public class InventoryUtils {

    private static @Nullable JavaPlugin plugin;

    /**
     * Initializes the inventory utilities with the given plugin instance.
     * Registers event listeners necessary for handling inventory events.
     *
     * @param plugin The instance of the JavaPlugin.
     * @throws IllegalArgumentException If the plugin instance is null.
     */
    public static void init(@NotNull JavaPlugin plugin) {
        InventoryUtils.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new InventoryEvents(), plugin);
    }

    /**
     * Returns the plugin instance associated with this class.
     *
     * @return The plugin instance.
     */
    public static JavaPlugin get() {
        return plugin;
    }
}