package dev.vansen.inventoryutils.item;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * A builder class for creating and customizing ItemStack objects.
 */
@SuppressWarnings({"unused", "deprecation"})
public class ItemBuilder {

    private final ItemStack itemStack;

    /**
     * Constructs a new ItemBuilder instance for the specified material.
     *
     * @param material The material of the item.
     */
    public ItemBuilder(@NotNull Material material) {
        this.itemStack = new ItemStack(material);
    }

    /**
     * Creates a new ItemBuilder instance for the specified material.
     *
     * @param material The material of the item.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder create(@NotNull Material material) {
        return new ItemBuilder(material);
    }

    /**
     * Gets the underlying ItemStack.
     *
     * @return The ItemStack.
     */
    public @NotNull ItemStack get() {
        return itemStack;
    }

    /**
     * Sets the display name of the ItemStack using a string.
     *
     * @param name The new display name.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder name(@NotNull String name) {
        return meta(meta -> meta.setDisplayName(name));
    }

    /**
     * Sets the display name of the ItemStack using a Component.
     *
     * @param name The new display name as a Component.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder name(@NotNull Component name) {
        return meta(meta -> meta.displayName(name));
    }

    /**
     * Sets the amount of items in the stack.
     *
     * @param amount The new amount of items.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * Sets the ItemMeta of the ItemStack using a provided meta consumer.
     *
     * @param metaConsumer The consumer to modify the ItemMeta.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder meta(@NotNull Consumer<ItemMeta> metaConsumer) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            metaConsumer.accept(meta);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Adds an enchantment to the ItemStack.
     *
     * @param enchantment            The enchantment to add.
     * @param level                  The level of the enchantment.
     * @param ignoreLevelRestriction Whether to ignore level restrictions.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder enchant(@NotNull Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        return meta(meta -> meta.addEnchant(enchantment, level, ignoreLevelRestriction));
    }

    /**
     * Adds an enchantment to the ItemStack.
     *
     * @param enchantment The enchantment to add.
     * @param level       The level of the enchantment.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder enchant(@NotNull Enchantment enchantment, int level) {
        return enchant(enchantment, level, true);
    }
}
