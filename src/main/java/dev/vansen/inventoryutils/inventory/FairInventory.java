package dev.vansen.inventoryutils.inventory;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.vansen.inventoryutils.InventoryUtils;
import dev.vansen.inventoryutils.item.ItemBuilder;
import dev.vansen.inventoryutils.item.ItemUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A customizable inventory class that handles inventory events and actions.
 */
@SuppressWarnings({"unused", "deprecation"})
public class FairInventory implements InventoryHolder {

    private final ConcurrentHashMap<Integer, ItemUtils> itemMap = new ConcurrentHashMap<>();
    private Inventory inventory;
    private Consumer<InventoryOpenEvent> openAction;
    private Consumer<InventoryCloseEvent> closeAction;
    private BiConsumer<InventoryAction, InventoryClickEvent> actionHandler;
    private BiConsumer<ClickType, InventoryClickEvent> clickTypeHandler;
    private BiConsumer<InventoryDragEvent, Player> dragHandler;
    private Predicate<InventoryCloseEvent> preventCloseCondition;
    private Predicate<InventoryClickEvent> itemClickCondition;
    private Predicate<InventoryClickEvent> cancelClicksCondition;

    /**
     * Constructs a new FairInventory instance with the given inventory.
     *
     * @param inventory The inventory to wrap.
     */
    public FairInventory(@NotNull Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Constructs a new FairInventory instance with the given title and number of rows.
     *
     * @param title The title of the inventory.
     * @param size  The number of rows in the inventory.
     */
    public FairInventory(@NotNull String title, @NotNull InventorySize size) {
        this.inventory = Bukkit.createInventory(this, size.get(), title);
    }

    /**
     * Constructs a new FairInventory instance with the given title and number of rows.
     *
     * @param title The title of the inventory as a Component.
     * @param size  The number of rows in the inventory.
     */
    public FairInventory(@NotNull Component title, @NotNull InventorySize size) {
        this.inventory = Bukkit.createInventory(this, size.get(), title);
    }

    /**
     * Creates a new FairInventory instance with the given title and number of rows.
     *
     * @param title The title of the inventory.
     * @param size  The number of rows in the inventory.
     * @return A new FairInventory instance.
     */
    public static FairInventory create(@NotNull String title, @NotNull InventorySize size) {
        return new FairInventory(title, InventorySize.size(size.get()));
    }

    /**
     * Creates a new FairInventory instance with the given title and number of rows.
     *
     * @param title The title of the inventory as a Component.
     * @param size  The number of rows in the inventory.
     * @return A new FairInventory instance.
     */
    public static FairInventory create(@NotNull Component title, @NotNull InventorySize size) {
        return new FairInventory(title, InventorySize.size(size.get()));
    }

    /**
     * Creates a new FairInventory instance with the given inventory.
     *
     * @param inventory The inventory to wrap.
     * @return A new FairInventory instance.
     */
    public static FairInventory create(@NotNull Inventory inventory) {
        return new FairInventory(inventory);
    }

    /**
     * Gets the inventory.
     *
     * @return The inventory.
     */
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item The ItemUtils instance representing the item to add.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory add(@NotNull ItemUtils item) {
        inventory.addItem(item.get());
        itemMap.put(inventory.first(item.get()), item);
        return this;
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item The ItemStack instance representing the item to add.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory add(@NotNull ItemStack item) {
        inventory.addItem(item);
        return this;
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item The ItemBuilder instance representing the item to add.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory add(@NotNull ItemBuilder item) {
        return add(item.build().get());
    }

    /**
     * Adds multiple items to the inventory.
     *
     * @param items The ItemUtils instances representing the items to add.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory add(@NotNull ItemUtils... items) {
        Arrays.stream(items)
                .forEach(this::add);
        return this;
    }

    /**
     * Adds multiple items to the inventory.
     *
     * @param items The ItemStack instances representing the items to add.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory add(@NotNull ItemStack... items) {
        Arrays.stream(items)
                .forEach(this::add);
        return this;
    }

    /**
     * Adds multiple items to the inventory.
     *
     * @param items The ItemBuilder instances representing the items to add.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory add(@NotNull ItemBuilder... items) {
        Arrays.stream(items)
                .forEach(this::add);
        return this;
    }

    /**
     * Sets an item at a specific slot in the inventory.
     *
     * @param slot The slot to set the item in (1-based index).
     * @param item The ItemUtils instance representing the item to set.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory set(int slot, @NotNull ItemUtils item) {
        inventory.setItem(slot - 1, item.get()); // Adjusting for 1-based indexing
        itemMap.put(slot - 1, item);
        return this;
    }

    /**
     * Sets an item at a specific slot in the inventory.
     *
     * @param slot The slot to set the item in (1-based index).
     * @param item The ItemStack instance representing the item to set.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory set(int slot, @NotNull ItemStack item) {
        inventory.setItem(slot - 1, item); // Adjusting for 1-based indexing
        return this;
    }

    /**
     * Sets an item at a specific slot in the inventory.
     *
     * @param slot The slot to set the item in (0-based index).
     * @param item The ItemUtils instance representing the item to set.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory set(int slot, @NotNull ItemUtils item, boolean disable) {
        inventory.setItem(slot, item.get());
        itemMap.put(slot, item);
        return this;
    }

    /**
     * Sets an item at a specific slot in the inventory.
     *
     * @param slot The slot to set the item in (0-based index).
     * @param item The ItemStack instance representing the item to set.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory set(int slot, @NotNull ItemStack item, boolean disable) {
        inventory.setItem(slot, item);
        return this;
    }

    /**
     * Sets the action to be performed when the inventory is opened.
     *
     * @param action The action to perform on inventory open.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory open(@NotNull Consumer<InventoryOpenEvent> action) {
        this.openAction = action;
        return this;
    }

    /**
     * Sets the action to be performed when the inventory is closed.
     *
     * @param action The action to perform on inventory close.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory close(@NotNull Consumer<InventoryCloseEvent> action) {
        this.closeAction = action;
        return this;
    }

    /**
     * Sets the handler for inventory actions.
     *
     * @param handler The handler for inventory actions.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory action(@NotNull BiConsumer<InventoryAction, InventoryClickEvent> handler) {
        this.actionHandler = handler;
        return this;
    }

    /**
     * Sets the handler for click types.
     *
     * @param handler The handler for click types.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory clickType(@NotNull BiConsumer<ClickType, InventoryClickEvent> handler) {
        this.clickTypeHandler = handler;
        return this;
    }

    /**
     * Sets whether to prevent the inventory from closing.
     *
     * @param prevent Whether to prevent the inventory from closing.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory preventClose(boolean prevent) {
        this.preventCloseCondition = event -> prevent;
        return this;
    }

    /**
     * Sets a condition for preventing the inventory from closing.
     *
     * @param condition The condition to check for preventing close.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory preventCloseIf(@NotNull Predicate<InventoryCloseEvent> condition) {
        this.preventCloseCondition = condition;
        return this;
    }

    /**
     * Sets whether to prevent clicks on the inventory.
     *
     * @param prevent Whether to prevent clicks on the inventory.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory preventClicks(boolean prevent) {
        this.cancelClicksCondition = event -> prevent;
        return this;
    }

    /**
     * Sets a condition for preventing clicks on the inventory.
     *
     * @param condition The condition to check for preventing clicks.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory preventClicksIf(@NotNull Predicate<InventoryClickEvent> condition) {
        this.cancelClicksCondition = condition;
        return this;
    }

    /**
     * Sets a condition to determine if a click event should be handled.
     *
     * @param condition The condition to check for handling clicks.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory itemClickCondition(@NotNull Predicate<InventoryClickEvent> condition) {
        this.itemClickCondition = condition;
        return this;
    }

    /**
     * Sets the handler for inventory drag events.
     *
     * @param handler The handler for inventory drag events.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory drag(@NotNull BiConsumer<InventoryDragEvent, Player> handler) {
        this.dragHandler = handler;
        return this;
    }

    /**
     * Creates a new inventory and sets the title of the inventory. The inventory contents are preserved.
     *
     * @param title The new title of the inventory.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory change(@NotNull String title) {
        Inventory newInventory = Bukkit.createInventory(this, inventory.getSize(), title);
        newInventory.setContents(inventory.getContents());
        this.inventory = newInventory;
        return this;
    }

    /**
     * Makes a new inventory, The inventory contents are preserved.
     *
     * @param title The new title of the inventory.
     * @param size  The new number of rows in the inventory.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory change(@NotNull String title, @NotNull InventorySize size) {
        Inventory newInventory = Bukkit.createInventory(this, size.get(), title);
        newInventory.setContents(inventory.getContents());
        this.inventory = newInventory;
        return this;
    }

    /**
     * Creates a new inventory and sets the title of the inventory. The inventory contents are preserved.
     *
     * @param title The new title of the inventory.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory change(@NotNull Component title) {
        Inventory newInventory = Bukkit.createInventory(this, inventory.getSize(), title);
        newInventory.setContents(inventory.getContents());
        this.inventory = newInventory;
        return this;
    }

    /**
     * Gets the item in the given slot.
     *
     * @param slot The slot to get the item from.
     * @return The item in the given slot.
     */
    public ItemStack get(int slot) {
        return inventory.getItem(slot);
    }

    /**
     * Gets the index of the first occurrence of the given item in the inventory.
     *
     * @param item The item to search for.
     * @return The index of the first occurrence of the item, or -1 if not found.
     */
    public int first(ItemStack item) {
        return inventory.first(item);
    }

    /**
     * Gets the index of the first occurrence of the given item in the inventory.
     *
     * @param item The item to search for, built from an ItemBuilder.
     * @return The index of the first occurrence of the item, or -1 if not found.
     */
    public int first(@NotNull ItemBuilder item) {
        return inventory.first(item.build().get());
    }

    /**
     * Gets the index of the first occurrence of the given item in the inventory.
     *
     * @param item The item to search for, wrapped in an ItemUtils instance.
     * @return The index of the first occurrence of the item, or -1 if not found.
     */
    public int first(@NotNull ItemUtils item) {
        return inventory.first(item.get());
    }

    /**
     * Gets the index of the first empty slot in the inventory.
     *
     * @return The index of the first empty slot, or -1 if the inventory is full.
     */
    public int firstEmpty() {
        return inventory.firstEmpty();
    }

    /**
     * Checks if the inventory contains the given item.
     *
     * @param item The item to search for.
     * @return True if the item is found, false otherwise.
     */
    public boolean contains(@NotNull ItemStack item) {
        return inventory.contains(item);
    }

    /**
     * Checks if the inventory contains the given item.
     *
     * @param item The item to search for, built from an ItemBuilder.
     * @return True if the item is found, false otherwise.
     */
    public boolean contains(@NotNull ItemBuilder item) {
        return inventory.contains(item.build().get());
    }

    /**
     * Checks if the inventory contains the given item.
     *
     * @param item The item to search for, wrapped in an ItemUtils instance.
     * @return True if the item is found, false otherwise.
     */
    public boolean contains(@NotNull ItemUtils item) {
        return inventory.contains(item.get());
    }

    /**
     * Checks if the inventory contains at least the given amount of the item.
     *
     * @param item   The item to search for.
     * @param amount The minimum amount required.
     * @return True if the item is found in the required amount, false otherwise.
     */
    public boolean containsAtLeast(@NotNull ItemStack item, int amount) {
        return inventory.containsAtLeast(item, amount);
    }

    /**
     * Checks if the inventory contains at least the given amount of the item.
     *
     * @param item   The item to search for, built from an ItemBuilder.
     * @param amount The minimum amount required.
     * @return True if the item is found in the required amount, false otherwise.
     */
    public boolean containsAtLeast(@NotNull ItemBuilder item, int amount) {
        return inventory.containsAtLeast(item.build().get(), amount);
    }

    /**
     * Checks if the inventory contains at least the given amount of the item.
     *
     * @param item   The item to search for, wrapped in an ItemUtils instance.
     * @param amount The minimum amount required.
     * @return True if the item is found in the required amount, false otherwise.
     */
    public boolean containsAtLeast(@NotNull ItemUtils item, int amount) {
        return inventory.containsAtLeast(item.get(), amount);
    }

    /**
     * Clears the whole inventory.
     */
    public void clear() {
        inventory.clear();
    }

    /**
     * Clears an item at a specific slot.
     *
     * @param slot The slot to clear.
     */
    public void clear(int slot) {
        inventory.clear(slot);
    }

    /**
     * Gets the size of the inventory.
     *
     * @return The number of slots in the inventory.
     */
    public int size() {
        return inventory.getSize();
    }

    /**
     * Gets the number of rows in the inventory.
     *
     * @return The number of rows in the inventory.
     */
    public int rows() {
        return (int) Math.ceil(size() / 9.0);
    }

    /**
     * Removes the given items from the inventory.
     *
     * @param items The items to remove.
     */
    public void remove(@NotNull ItemStack... items) {
        inventory.removeItem(items);
    }

    /**
     * Removes the given items from the inventory.
     *
     * @param items The items to remove, built from ItemBuilders.
     */
    public void remove(@NotNull ItemBuilder... items) {
        Arrays.stream(items).forEach(item -> inventory.removeItem(item.build().get()));
    }

    /**
     * Removes the given items from the inventory.
     *
     * @param items The items to remove, wrapped in ItemUtils instances.
     */
    public void remove(@NotNull ItemUtils... items) {
        Arrays.stream(items).forEach(item -> inventory.removeItem(item.get()));
    }

    /**
     * Removes the given item from any slot in the inventory.
     *
     * @param item The item to remove.
     */
    public void removeAnySlot(@NotNull ItemStack... item) {
        inventory.removeItemAnySlot(item);
    }

    /**
     * Gets the contents of the inventory.
     * <p>
     * This does not use {@link org.jetbrains.annotations.Nullable} or {@link NotNull} annotations to provide better quality over code, however, It's still recommended to handle null values.
     *
     * @return The contents of the inventory.
     */
    public ItemStack[] contents() {
        return inventory.getContents();
    }

    /**
     * Removes the given item from any slot in the inventory.
     *
     * @param item The item to remove, built from an ItemBuilder.
     */
    public void removeAnySlot(@NotNull ItemBuilder... item) {
        Arrays.stream(item).forEach(itemBuilder -> inventory.removeItemAnySlot(itemBuilder.build().get()));
    }

    /**
     * Removes the given item from any slot in the inventory.
     *
     * @param item The item to remove, wrapped in an ItemUtils instance.
     */
    public void removeAnySlot(@NotNull ItemUtils... item) {
        Arrays.stream(item).forEach(itemUtils -> inventory.removeItemAnySlot(itemUtils.get()));
    }

    /**
     * Makes a new inventory, The inventory contents are preserved.
     *
     * @param title The new title of the inventory.
     * @param size  The new number of rows in the inventory.
     * @return The current FairInventory instance.
     */
    @CanIgnoreReturnValue
    public FairInventory change(@NotNull Component title, @NotNull InventorySize size) {
        Inventory newInventory = Bukkit.createInventory(this, size.get(), title);
        newInventory.setContents(inventory.getContents());
        this.inventory = newInventory;
        return this;
    }

    /**
     * Handles the inventory open event.
     *
     * @param event The InventoryOpenEvent.
     */
    public void handleOpen(@NotNull InventoryOpenEvent event) {
        if (openAction != null) openAction.accept(event);
    }

    /**
     * Handles the inventory close event.
     *
     * @param event The InventoryCloseEvent.
     */
    public void handleClose(@NotNull InventoryCloseEvent event) {
        if (preventCloseCondition != null && preventCloseCondition.test(event))
            Bukkit.getScheduler().scheduleSyncDelayedTask(InventoryUtils.get(), () -> event.getPlayer().openInventory(inventory), 1L);
        else if (closeAction != null) closeAction.accept(event);
    }

    /**
     * Handles the inventory click event.
     *
     * @param event The InventoryClickEvent.
     */
    public void handleClick(@NotNull InventoryClickEvent event) {
        if (cancelClicksCondition != null && cancelClicksCondition.test(event)) event.setCancelled(true);
        if (itemClickCondition != null && !itemClickCondition.test(event)) return;

        ItemUtils item = itemMap.get(event.getSlot());
        if (item != null) item.itemClick().handleClick(event);
        if (actionHandler != null) actionHandler.accept(event.getAction(), event);
        if (clickTypeHandler != null) clickTypeHandler.accept(event.getClick(), event);
    }

    /**
     * Handles the inventory drag event.
     *
     * @param event The InventoryDragEvent.
     */
    public void handleDrag(@NotNull InventoryDragEvent event) {
        if (dragHandler != null) dragHandler.accept(event, (Player) event.getWhoClicked());
    }

    /**
     * Opens the inventory for a player.
     *
     * @param player The player to open the inventory for.
     */
    public void show(@NotNull Player player) {
        Bukkit.getScheduler().runTask(InventoryUtils.get(), () -> player.openInventory(inventory)); // Thread safe
    }
}