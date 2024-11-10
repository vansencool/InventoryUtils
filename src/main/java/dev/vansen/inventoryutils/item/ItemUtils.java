package dev.vansen.inventoryutils.item;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * A utility class that provides a simplified interface for managing items in Minecraft.
 */
@SuppressWarnings("unused")
public final class ItemUtils {
    private final ItemStack item;
    private final ItemClick itemClick;

    /**
     * Constructs a new ItemUtils instance with the specified ItemStack.
     *
     * @param itemStack The ItemStack to manage.
     */
    public ItemUtils(@NotNull ItemStack itemStack) {
        this.item = itemStack;
        this.itemClick = ItemClick.of();
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
     * Gets the ItemClick for managing item interactions.
     *
     * @return The ItemClick instance.
     */
    public @NotNull ItemClick itemClick() {
        return itemClick;
    }

    /**
     * Sets the click event handler for the item.
     *
     * @return The ItemUtils instance.
     */
    @CanIgnoreReturnValue
    public @NotNull ItemUtils click(@NotNull Consumer<InventoryClickEvent> event) {
        itemClick.click(event);
        return this;
    }

    /**
     * Gets the underlying ItemStack.
     *
     * @return The ItemStack.
     */
    public @NotNull ItemStack get() {
        return item;
    }
}