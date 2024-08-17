package dev.vansen.inventoryutils;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

/**
 * A utility class for managing and customizing items in Minecraft.
 */
@SuppressWarnings("unused")
public class ItemUtils {

    private final ItemStack itemStack;
    private Consumer<InventoryClickEvent> clickAction;

    /**
     * Constructs a new ItemUtils instance for the specified material.
     *
     * @param material The material of the item.
     */
    public ItemUtils(Material material) {
        this.itemStack = new ItemStack(material);
    }

    /**
     * Creates a new ItemUtils instance for the specified material.
     *
     * @param material The material of the item.
     * @return A new ItemUtils instance.
     */
    public static ItemUtils create(Material material) {
        return new ItemUtils(material);
    }

    /**
     * Sets the action to be performed when the item is clicked.
     *
     * @param action The action to perform on item click.
     * @return The current ItemUtils instance.
     */
    public ItemUtils click(Consumer<InventoryClickEvent> action) {
        this.clickAction = action;
        return this;
    }

    /**
     * Handles a click event by executing the click action, if set.
     *
     * @param event The InventoryClickEvent to handle.
     */
    public void handleClick(InventoryClickEvent event) {
        if (clickAction != null) {
            clickAction.accept(event);
        }
    }

    /**
     * Gets the underlying ItemStack.
     *
     * @return The ItemStack.
     */
    public ItemStack get() {
        return itemStack;
    }

    /**
     * Gets the ItemMeta of the ItemStack.
     *
     * @return The ItemMeta of the ItemStack.
     */
    public ItemMeta meta() {
        return itemStack.getItemMeta();
    }

    /**
     * Sets the ItemMeta of the ItemStack using a provided meta consumer.
     *
     * @param metaConsumer The consumer to modify the ItemMeta.
     * @return The current ItemUtils instance.
     */
    public ItemUtils meta(Consumer<ItemMeta> metaConsumer) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            metaConsumer.accept(meta);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Sets the display name of the ItemStack.
     *
     * @param name The new display name.
     * @return The current ItemUtils instance.
     */
    public ItemUtils name(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Sets the display name of the ItemStack using a Component.
     *
     * @param name The new display name as a Component.
     * @return The current ItemUtils instance.
     */
    public ItemUtils name(Component name) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.displayName(name);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Sets the amount of items in the stack.
     *
     * @param amount The new amount of items.
     * @return The current ItemUtils instance.
     */
    public ItemUtils amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * Sets the durability of the ItemStack.
     *
     * @param durability The new durability value.
     * @return The current ItemUtils instance.
     */
    public ItemUtils durability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    /**
     * Sets the damage value of the ItemStack.
     *
     * @param damage The new damage value.
     * @return The current ItemUtils instance.
     */
    public ItemUtils damage(int damage) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            Damageable damageable = (Damageable) meta;
            damageable.setDamage(damage);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Sets the enchantments of the ItemStack.
     *
     * @return The current ItemUtils instance.
     */
    public ItemUtils enchants() {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.getEnchants(); // This line seems to be redundant; it doesn't modify the meta.
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
     * @return The current ItemUtils instance.
     */
    public ItemUtils enchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.addEnchant(enchantment, level, ignoreLevelRestriction);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Adds an enchantment to the ItemStack.
     *
     * @param enchantment The enchantment to add.
     * @param level       The level of the enchantment.
     * @return The current ItemUtils instance.
     */
    public ItemUtils enchant(Enchantment enchantment, int level) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.addEnchant(enchantment, level, true);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Sets whether the ItemStack is unbreakable.
     *
     * @param unbreakable Whether the item should be unbreakable.
     * @return The current ItemUtils instance.
     */
    public ItemUtils unbreakable(boolean unbreakable) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setUnbreakable(unbreakable);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Sets the lore of the ItemStack using an array of strings.
     *
     * @param lore The new lore as an array of strings.
     * @return The current ItemUtils instance.
     */
    public ItemUtils setLore(String... lore) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setLore(List.of(lore));
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Adds item flags to the ItemStack.
     *
     * @param flags The item flags to add.
     * @return The current ItemUtils instance.
     */
    public ItemUtils itemFlags(ItemFlag... flags) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.addItemFlags(flags);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Sets the lore of the ItemStack using a list of strings.
     *
     * @param lore The new lore as a list of strings.
     * @return The current ItemUtils instance.
     */
    public ItemUtils setLore(List<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setLore(lore);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Sets the lore of the ItemStack using a list of Components.
     *
     * @param lore The new lore as a list of Components.
     * @return The current ItemUtils instance.
     */
    public ItemUtils lore(List<Component> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.lore(lore);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    /**
     * Sets the lore of the ItemStack using an array of Components.
     *
     * @param lore The new lore as an array of Components.
     * @return The current ItemUtils instance.
     */
    public ItemUtils lore(Component... lore) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.lore(List.of(lore));
            itemStack.setItemMeta(meta);
        }
        return this;
    }
}