package dev.vansen.inventoryutils.item;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * A utility class that provides a simplified interface for managing items in Minecraft.
 */
@SuppressWarnings("unused")
public class ItemUtils {
    private final ItemStack item;
    private final ItemInteractionHandler itemInteractionHandler;

    /**
     * Constructs a new ItemUtils instance with the specified ItemStack.
     *
     * @param itemStack The ItemStack to manage.
     */
    public ItemUtils(@NotNull ItemStack itemStack) {
        this.item = itemStack;
        this.itemInteractionHandler = new ItemInteractionHandler(item);
    }

    /**
     * Returns an ItemBuilder instance for further customization on the current ItemUtils state.
     *
     * @return An ItemUtils instance.
     */
    public @NotNull ItemBuilder builder() {
        return new ItemBuilder(item);
    }

    /**
     * Gets the ItemInteractionHandler for managing item interactions.
     *
     * @return The ItemInteractionHandler instance.
     */
    public @NotNull ItemInteractionHandler interactionHandler() {
        return itemInteractionHandler;
    }

    /**
     * Sets the click event handler for the item.
     *
     * @return The ItemUtils instance.
     */
    public @NotNull ItemUtils click(@NotNull Consumer<InventoryClickEvent> event) {
        itemInteractionHandler.click(event);
        return this;
    }

    public @NotNull ItemStack get() {
        return item;
    }
}