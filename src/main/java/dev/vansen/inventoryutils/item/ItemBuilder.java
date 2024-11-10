package dev.vansen.inventoryutils.item;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A builder class for creating and customizing ItemStack objects.
 */
@SuppressWarnings({"unused", "deprecation"})
public final class ItemBuilder {

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
     * Constructs a new ItemBuilder instance for the specified ItemStack.
     *
     * @param itemStack The ItemStack.
     */
    public ItemBuilder(@NotNull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Constructs a new ItemBuilder instance for the specified material and amount.
     *
     * @param material The material of the item.
     * @param amount   The amount of the item.
     */
    public ItemBuilder(@NotNull Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    /**
     * Creates a new ItemBuilder instance for the specified material.
     *
     * @param material The material of the item.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder from(@NotNull Material material) {
        return new ItemBuilder(material);
    }

    /**
     * Creates a new ItemBuilder instance for the specified ItemStack.
     *
     * @param itemStack The ItemStack.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder from(@NotNull ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    /**
     * Creates a new ItemBuilder instance for the specified material and amount.
     *
     * @param material The material of the item.
     * @param amount   The amount of the item.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder from(@NotNull Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    /**
     * Creates a new ItemBuilder instance from a serialized byte array.
     *
     * @param serialized The serialized byte array.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder from(byte @NotNull [] serialized) {
        return new ItemBuilder(ItemStack.deserializeBytes(serialized));
    }

    /**
     * Creates a new ItemBuilder instance from a serialized map.
     *
     * @param serialized The serialized map.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder from(@NotNull Map<String, Object> serialized) {
        return new ItemBuilder(ItemStack.deserialize(serialized));
    }

    /**
     * Alternative alias for {@link #from(Material)}
     *
     * @param material The material of the item.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder create(@NotNull Material material) {
        return from(material);
    }

    /**
     * Alternative alias for {@link #from(ItemStack)}
     *
     * @param itemStack The ItemStack.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder create(@NotNull ItemStack itemStack) {
        return from(itemStack);
    }

    /**
     * Alternative alias for {@link #from(Material, int)}
     *
     * @param material The material of the item.
     * @param amount   The amount of the item.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder create(@NotNull Material material, int amount) {
        return from(material, amount);
    }

    /**
     * Alternative alias for {@link #from(byte[])}
     *
     * @param serialized The serialized byte array.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder create(byte @NotNull [] serialized) {
        return from(serialized);
    }

    /**
     * Alternative alias for {@link #from(Map)}
     *
     * @param serialized The serialized map.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder create(@NotNull Map<String, Object> serialized) {
        return from(serialized);
    }

    /**
     * Alternative alias for {@link #from(Material)}
     *
     * @param material The material of the item.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder of(@NotNull Material material) {
        return from(material);
    }

    /**
     * Alternative alias for {@link #from(ItemStack)}
     *
     * @param itemStack The ItemStack.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder of(@NotNull ItemStack itemStack) {
        return from(itemStack);
    }

    /**
     * Alternative alias for {@link #from(Material, int)}
     *
     * @param material The material of the item.
     * @param amount   The amount of the item.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder of(@NotNull Material material, int amount) {
        return from(material, amount);
    }

    /**
     * Alternative alias for {@link #from(byte[])}
     *
     * @param serialized The serialized byte array.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder of(byte @NotNull [] serialized) {
        return from(serialized);
    }

    /**
     * Alternative alias for {@link #from(Map)}
     *
     * @param serialized The serialized map.
     * @return A new ItemBuilder instance.
     */
    public static ItemBuilder of(@NotNull Map<String, Object> serialized) {
        return from(serialized);
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
     * Sets the lore of the ItemStack using an array of strings.
     *
     * @param lore The lore as an array of strings.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder setLore(@NotNull String... lore) {
        return meta(meta -> meta.setLore(Arrays.asList(lore)));
    }

    /**
     * Sets the lore of the ItemStack using a list of strings.
     *
     * @param lore The lore as a list of strings.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder setLore(@NotNull List<String> lore) {
        return meta(meta -> meta.setLore(lore));
    }

    /**
     * Sets the lore of the ItemStack using an array of Components.
     *
     * @param lore The lore as an array of Components.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder lore(@NotNull Component... lore) {
        return meta(meta -> meta.lore(Arrays.asList(lore)));
    }

    /**
     * Sets the lore of the ItemStack using a list of Components.
     *
     * @param lore The lore as a list of Components.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder lore(@NotNull List<Component> lore) {
        return meta(meta -> meta.lore(lore));
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
     * Adds item flags to the ItemStack.
     *
     * @param flags The item flags to add.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder itemFlags(@NotNull ItemFlag... flags) {
        return meta(meta -> meta.addItemFlags(flags));
    }

    /**
     * Alternative alias for {@link #itemFlags(ItemFlag...)}
     *
     * @param flags The item flags to add.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder flags(@NotNull ItemFlag... flags) {
        return itemFlags(flags);
    }

    /**
     * Removes item flags from the ItemStack.
     *
     * @param flags The item flags to remove.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder removeItemFlags(@NotNull ItemFlag... flags) {
        return meta(meta -> meta.removeItemFlags(flags));
    }

    /**
     * Alternative alias for {@link #removeItemFlags(ItemFlag...)}
     *
     * @param flags The item flags to remove.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder removeFlags(@NotNull ItemFlag... flags) {
        return removeItemFlags(flags);
    }

    /**
     * Sets a custom model data value for the ItemStack.
     *
     * @param modelData The custom model data value.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder customModelData(int modelData) {
        return meta(meta -> meta.setCustomModelData(modelData));
    }

    /**
     * Alternative alias for {@link #customModelData(int)}
     *
     * @param modelData The custom model data value.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder modelData(int modelData) {
        return customModelData(modelData);
    }

    /**
     * Sets the unbreakable property of the ItemStack to true.
     *
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder unbreakable() {
        return meta(meta -> meta.setUnbreakable(true));
    }

    /**
     * Sets the unbreakable property of the ItemStack.
     *
     * @param unbreakable True if the item should be unbreakable, false otherwise.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder unbreakable(boolean unbreakable) {
        return meta(meta -> meta.setUnbreakable(unbreakable));
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

    /**
     * Adds an enchantment to the ItemStack.
     *
     * @param enchantment The enchantment to add.
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder enchant(@NotNull Enchantment enchantment) {
        return enchant(enchantment, 1);
    }

    /**
     * Serializes the ItemStack into a byte array.
     *
     * @return The serialized byte array.
     */
    public byte @NotNull [] serializeBytes() {
        return itemStack.serializeAsBytes();
    }

    /**
     * Serializes the ItemStack into a map, generally not recommended, use {@link #serializeBytes()}
     *
     * @return The serialized map.
     * @see #serializeBytes()
     */
    public Map<String, Object> serialize() {
        return itemStack.serialize();
    }

    /**
     * Ensures that the ItemStack conversations are processed for this ItemStack.
     *
     * @return The current ItemBuilder instance.
     */
    @CanIgnoreReturnValue
    public ItemBuilder ensureVersions() {
        itemStack.ensureServerConversions();
        return this;
    }

    /**
     * Builds and returns an ItemUtils instance.
     *
     * @return An ItemUtils instance.
     */
    public ItemUtils build() {
        return new ItemUtils(this.itemStack);
    }
}