package dev.vansen.inventoryutils.item;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * A handler class for managing item clicks.
 */
@SuppressWarnings("unused")
public final class ItemClick {

    public Consumer<InventoryClickEvent> clickAction;

    public static ItemClick of() {
        return new ItemClick();
    }

    /**
     * Sets the action to be performed when the item is clicked.
     *
     * @param action The action to perform on item click.
     * @return The current ItemClick instance.
     */
    @CanIgnoreReturnValue
    public ItemClick click(@NotNull Consumer<InventoryClickEvent> action) {
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
}