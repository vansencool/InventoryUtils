package dev.vansen.inventoryutils.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof FairInventory fairInventory) {
            fairInventory.handleClick(event);
        }
    }

    @EventHandler
    public void onInventoryOpen(final @NotNull InventoryOpenEvent event) {
        if (event.getInventory().getHolder() instanceof FairInventory fairInventory) {
            fairInventory.handleOpen(event);
        }
    }

    @EventHandler
    public void onInventoryClose(final @NotNull InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof FairInventory fairInventory) {
            fairInventory.handleClose(event);
        }
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent event) {
        if (event.getInventory().getHolder() instanceof FairInventory fairInventory) {
            fairInventory.handleDrag(event);
        }
    }
}