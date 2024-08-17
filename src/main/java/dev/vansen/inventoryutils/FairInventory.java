package dev.vansen.inventoryutils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A customizable inventory class that handles inventory events and actions.
 */
public class FairInventory implements InventoryHolder {

    private Inventory inventory;
    private Consumer<InventoryOpenEvent> openAction;
    private Consumer<InventoryCloseEvent> closeAction;
    private BiConsumer<InventoryAction, InventoryClickEvent> actionHandler;
    private BiConsumer<ClickType, InventoryClickEvent> clickTypeHandler;
    private BiConsumer<InventoryDragEvent, Player> dragHandler;
    private Predicate<InventoryCloseEvent> preventCloseCondition;
    private Predicate<InventoryClickEvent> itemClickCondition;

    private final Map<Integer, ItemUtils> itemMap = new HashMap<>();

    private FairInventory(String title, int rows) {
        this.inventory = Bukkit.createInventory(this, rows * 9, title);
    }

    /**
     * Creates a new FairInventory instance with the given title and number of rows.
     *
     * @param title The title of the inventory.
     * @param rows  The number of rows in the inventory.
     * @return A new FairInventory instance.
     */
    public static FairInventory create(String title, int rows) {
        return new FairInventory(title, rows);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item The ItemUtils instance representing the item to add.
     * @return The current FairInventory instance.
     */
    public FairInventory add(ItemUtils item) {
        inventory.addItem(item.get());
        itemMap.put(inventory.first(item.get()), item);
        return this;
    }

    /**
     * Sets an item at a specific slot in the inventory.
     *
     * @param slot The slot to set the item in (1-based index).
     * @param item The ItemUtils instance representing the item to set.
     * @return The current FairInventory instance.
     */
    public FairInventory set(int slot, ItemUtils item) {
        inventory.setItem(slot - 1, item.get()); // Adjusting for 1-based indexing
        itemMap.put(slot - 1, item);
        return this;
    }

    /**
     * Sets the action to be performed when the inventory is opened.
     *
     * @param action The action to perform on inventory open.
     * @return The current FairInventory instance.
     */
    public FairInventory open(Consumer<InventoryOpenEvent> action) {
        this.openAction = action;
        return this;
    }

    /**
     * Sets the action to be performed when the inventory is closed.
     *
     * @param action The action to perform on inventory close.
     * @return The current FairInventory instance.
     */
    public FairInventory close(Consumer<InventoryCloseEvent> action) {
        this.closeAction = action;
        return this;
    }

    /**
     * Sets the handler for inventory actions.
     *
     * @param handler The handler for inventory actions.
     * @return The current FairInventory instance.
     */
    public FairInventory action(BiConsumer<InventoryAction, InventoryClickEvent> handler) {
        this.actionHandler = handler;
        return this;
    }

    /**
     * Sets the handler for click types.
     *
     * @param handler The handler for click types.
     * @return The current FairInventory instance.
     */
    public FairInventory clickType(BiConsumer<ClickType, InventoryClickEvent> handler) {
        this.clickTypeHandler = handler;
        return this;
    }

    /**
     * Sets whether to prevent the inventory from closing.
     *
     * @param prevent Whether to prevent the inventory from closing.
     * @return The current FairInventory instance.
     */
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
    public FairInventory preventCloseIf(Predicate<InventoryCloseEvent> condition) {
        this.preventCloseCondition = condition;
        return this;
    }

    /**
     * Sets a condition to determine if a click event should be handled.
     *
     * @param condition The condition to check for handling clicks.
     * @return The current FairInventory instance.
     */
    public FairInventory itemClickCondition(Predicate<InventoryClickEvent> condition) {
        this.itemClickCondition = condition;
        return this;
    }

    /**
     * Sets the handler for inventory drag events.
     *
     * @param handler The handler for inventory drag events.
     * @return The current FairInventory instance.
     */
    public FairInventory drag(BiConsumer<InventoryDragEvent, Player> handler) {
        this.dragHandler = handler;
        return this;
    }

    /**
     * Sets the title of the inventory. The inventory contents are preserved.
     *
     * @param title The new title of the inventory.
     * @return The current FairInventory instance.
     */
    public FairInventory title(String title) {
        // Create a new inventory with the updated title
        Inventory newInventory = Bukkit.createInventory(this, inventory.getSize(), title);
        newInventory.setContents(inventory.getContents());
        this.inventory = newInventory;
        return this;
    }

    /**
     * Handles the inventory open event.
     *
     * @param event The InventoryOpenEvent.
     */
    public void handleOpen(InventoryOpenEvent event) {
        if (openAction != null) {
            openAction.accept(event);
        }
    }

    /**
     * Handles the inventory close event.
     *
     * @param event The InventoryCloseEvent.
     */
    public void handleClose(InventoryCloseEvent event) {
        if (preventCloseCondition != null && preventCloseCondition.test(event)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(InventoryUtils.get(), () -> ((Player) event.getPlayer()).openInventory(inventory), 1L);
        } else if (closeAction != null) {
            closeAction.accept(event);
        }
    }

    /**
     * Handles the inventory click event.
     *
     * @param event The InventoryClickEvent.
     */
    public void handleClick(InventoryClickEvent event) {
        if (itemClickCondition != null && !itemClickCondition.test(event)) {
            return;
        }

        ItemUtils item = itemMap.get(event.getSlot());
        if (item != null) {
            item.handleClick(event);
        }
        if (actionHandler != null) {
            actionHandler.accept(event.getAction(), event);
        }
        if (clickTypeHandler != null) {
            clickTypeHandler.accept(event.getClick(), event);
        }
    }

    /**
     * Handles the inventory drag event.
     *
     * @param event The InventoryDragEvent.
     */
    public void handleDrag(InventoryDragEvent event) {
        if (dragHandler != null) {
            dragHandler.accept(event, (Player) event.getWhoClicked());
        }
    }

    /**
     * Opens the inventory for a player.
     *
     * @param player The player to open the inventory for.
     */
    public void show(Player player) {
        player.openInventory(inventory);
    }
}