package dev.vansen.inventoryutils;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Utility class for initializing and managing inventory-related functionality in a Bukkit plugin.
 */
public class InventoryUtils {

    private static JavaPlugin plugin;

    /**
     * Initializes the inventory utilities with the given plugin instance.
     * Registers event listeners necessary for handling inventory events.
     *
     * @param pluginInstance The instance of the JavaPlugin.
     */
    public static void init(JavaPlugin pluginInstance) {
        plugin = pluginInstance;
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