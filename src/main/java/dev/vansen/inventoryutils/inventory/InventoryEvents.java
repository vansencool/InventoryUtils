package dev.vansen.inventoryutils.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

public final class InventoryEvents implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof FairInventory fairInventory) {
            if (InventoryOptions.TRIGGER_CLICK_ON_MAIN_INVENTORY.value() && event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof FairInventory) {
                fairInventory.handleClick(event, true);
                return;
            }
            fairInventory.handleClick(event, false);
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