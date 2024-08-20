package dev.vansen.inventoryutils;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Utility class for initializing and managing inventory-related events.
 */
@SuppressWarnings("unused")
public class InventoryUtils {

    private static JavaPlugin plugin;

    /**
     * Initializes the inventory utilities with the given plugin instance.
     * Registers event listeners necessary for handling inventory events.
     *
     * @param plugin The instance of the JavaPlugin.
     * @throws IllegalArgumentException If the plugin instance is null.
     */
    public static void init(JavaPlugin plugin) {
        if (plugin == null) throw new IllegalArgumentException("Plugin cannot be null");
        InventoryUtils.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new InventoryEventListener(), plugin);
    }

    /**
     * Returns the plugin instance associated with this utility class.
     *
     * @return The plugin instance.
     */
    public static JavaPlugin get() {
        return plugin;
    }
}