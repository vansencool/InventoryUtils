package dev.vansen.inventoryutils.item;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class that provides a simplified interface for managing items in Minecraft.
 */
@SuppressWarnings("unused")
public class ItemUtils {

    private final ItemBuilder itemBuilder;
    private final ItemMetaManager itemMetaManager;
    private final ItemInteractionHandler itemInteractionHandler;

    /**
     * Constructs a new ItemUtils instance for the specified material.
     *
     * @param material The material of the item.
     */
    public ItemUtils(@NotNull Material material) {
        this.itemBuilder = new ItemBuilder(material);
        this.itemMetaManager = new ItemMetaManager(itemBuilder.get());
        this.itemInteractionHandler = new ItemInteractionHandler(itemBuilder.get());
    }

    /**
     * Gets the ItemBuilder for customizing the item.
     *
     * @return The ItemBuilder instance.
     */
    public @NotNull ItemBuilder builder() {
        return itemBuilder;
    }

    /**
     * Gets the ItemMetaManager for managing item metadata.
     *
     * @return The ItemMetaManager instance.
     */
    public @NotNull ItemMetaManager metaManager() {
        return itemMetaManager;
    }

    /**
     * Gets the ItemInteractionHandler for managing item interactions.
     *
     * @return The ItemInteractionHandler instance.
     */
    public @NotNull ItemInteractionHandler interactionHandler() {
        return itemInteractionHandler;
    }
}