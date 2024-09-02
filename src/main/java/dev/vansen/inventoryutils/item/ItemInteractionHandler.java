package dev.vansen.inventoryutils.item;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * A handler class for managing item interactions, such as clicks.
 */
@SuppressWarnings("unused")
public class ItemInteractionHandler {

    private final ItemStack itemStack;
    private Consumer<InventoryClickEvent> clickAction;

    /**
     * Constructs a new ItemInteractionHandler for the specified ItemStack.
     *
     * @param itemStack The ItemStack to manage interactions for.
     */
    public ItemInteractionHandler(@NotNull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Sets the action to be performed when the item is clicked.
     *
     * @param action The action to perform on item click.
     * @return The current ItemInteractionHandler instance.
     */
    @CanIgnoreReturnValue
    public ItemInteractionHandler click(@NotNull Consumer<InventoryClickEvent> action) {
        this.clickAction = action;
        return this;
    }

    /**
     * Handles a click event by executing the click action, if set.
     *
     * @param event The InventoryClickEvent to handle.
     */
    public void handleClick(@NotNull InventoryClickEvent event) {
        if (clickAction != null) {
            clickAction.accept(event);
        }
    }

    /**
     * Gets the underlying ItemStack.
     *
     * @return The ItemStack.
     */
    public @NotNull ItemStack get() {
        return itemStack;
    }
}