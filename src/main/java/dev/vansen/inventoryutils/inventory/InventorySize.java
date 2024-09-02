package dev.vansen.inventoryutils.inventory;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * Utility class for inventory size.
 * <p>
 * This class is used to define the size of an inventory.
 * <p>
 * There are two types of inventory size:
 * <ul>
 * <li>Size: The total number of slots in the inventory.</li>
 * <li>Rows: The number of rows in the inventory. This is used to calculate the size.</li>
 * </ul>
 * <p>
 * There are also methods to get the size from a given number of rows or slots.
 */
@SuppressWarnings("unused")
public class InventorySize {

    private final int size;

    private InventorySize(int size) {
        this.size = size;
    }

    /**
     * Creates an inventory size with the given number of slots.
     *
     * @param size The number of slots in the inventory.
     * @return A new InventorySize instance.
     */
    @CanIgnoreReturnValue
    public static InventorySize size(int size) {
        return new InventorySize(size);
    }

    /**
     * Creates an inventory size with the given number of rows.
     *
     * @param rows The number of rows in the inventory.
     * @return A new InventorySize instance.
     */
    @CanIgnoreReturnValue
    public static InventorySize rows(int rows) {
        return new InventorySize(rows * 9);
    }

    /**
     * Creates an inventory size with the given number of slots.
     * This method is used internally by the other methods.
     *
     * @param size The number of slots in the inventory.
     * @return A new InventorySize instance.
     */
    @CanIgnoreReturnValue
    public static InventorySize edit(int size) {
        return new InventorySize(size);
    }

    /**
     * Gets the size of the inventory.
     *
     * @return The number of slots in the inventory.
     */
    public int get() {
        return size;
    }

    /**
     * Gets the number of rows in the inventory.
     *
     * @return The number of rows in the inventory.
     */
    public int getRows() {
        return (int) Math.ceil(size / 9.0);
    }

    /**
     * Gets the number of slots in a given row.
     *
     * @return The number of slots in the row.
     */
    public int slotsPerRow() {
        return 9;
    }

    /**
     * Like method.
     * This method is used to calculate the size of the inventory
     * based on a given number of rows and slots per row.
     * <p>
     * For example, if you want to create an inventory with 3 rows
     * and 9 slots per row, you can use this method like this:
     * <pre>
     *     InventorySize.like(3, 9);
     * </pre>
     * It will return a new InventorySize instance with the size
     * of 27 (3 rows * 9 slots per row).
     *
     * @param rows        The number of rows in the inventory.
     * @param slotsPerRow The number of slots in each row.
     * @return A new InventorySize instance.
     */
    @CanIgnoreReturnValue
    public static InventorySize like(int rows, int slotsPerRow) {
        if (slotsPerRow != 9) throw new IllegalArgumentException("The number of slots per row must be 9");
        if (rows > 5) throw new IllegalArgumentException("The number of rows must be 5 or less");
        return new InventorySize(rows * slotsPerRow);
    }
}