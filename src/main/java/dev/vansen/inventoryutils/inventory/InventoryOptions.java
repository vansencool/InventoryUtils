package dev.vansen.inventoryutils.inventory;

@SuppressWarnings("unused")
public enum InventoryOptions {
    /**
     * If true, the click event will ONLY be triggered when the slot is in the main inventory (not in the player's inventory).
     * <p>
     * If false, the click event will be triggered when the slot is in the main inventory or in the player's inventory.
     */
    TRIGGER_CLICK_ON_MAIN_INVENTORY(true);

    boolean value;

    InventoryOptions(boolean value) {
        this.value = value;
    }

    /**
     * Returns the value of the option.
     *
     * @return The value of the option.
     */
    public boolean value() {
        return value;
    }

    /**
     * Sets the value of the option.
     *
     * @param value The value to set.
     */
    public void value(boolean value) {
        this.value = value;
    }
}
