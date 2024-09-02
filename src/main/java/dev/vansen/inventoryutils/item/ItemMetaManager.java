package dev.vansen.inventoryutils.item;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

/**
 * A utility class for managing the metadata of ItemStack objects.
 */
@SuppressWarnings({"unused", "deprecation"})
public class ItemMetaManager {

    private final ItemStack itemStack;

    /**
     * Constructs a new ItemMetaManager for the specified ItemStack.
     *
     * @param itemStack The ItemStack to manage.
     */
    public ItemMetaManager(@NotNull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Sets the lore of the ItemStack using a list of strings.
     *
     * @param lore The new lore as a list of strings.
     * @return The current ItemMetaManager instance.
     */
    @CanIgnoreReturnValue
    public ItemMetaManager setLore(@NotNull List<String> lore) {
        return meta(meta -> meta.setLore(lore));
    }

    /**
     * Sets the lore of the ItemStack using a list of Components.
     *
     * @param lore The new lore as a list of Components.
     * @return The current ItemMetaManager instance.
     */
    @CanIgnoreReturnValue
    public ItemMetaManager lore(@NotNull List<Component> lore) {
        return meta(meta -> meta.lore(lore));
    }

    /**
     * Adds item flags to the ItemStack.
     *
     * @param flags The item flags to add.
     * @return The current ItemMetaManager instance.
     */
    @CanIgnoreReturnValue
    public ItemMetaManager itemFlags(@NotNull ItemFlag... flags) {
        return meta(meta -> meta.addItemFlags(flags));
    }

    /**
     * Removes item flags from the ItemStack.
     *
     * @param flags The item flags to remove.
     * @return The current ItemMetaManager instance.
     */
    @CanIgnoreReturnValue
    public ItemMetaManager removeItemFlags(@NotNull ItemFlag... flags) {
        return meta(meta -> meta.removeItemFlags(flags));
    }

    /**
     * Sets a custom model data value for the ItemStack.
     *
     * @param modelData The custom model data value.
     * @return The current ItemMetaManager instance.
     */
    public ItemMetaManager customModelData(int modelData) {
        return meta(meta -> meta.setCustomModelData(modelData));
    }

    /**
     * Sets the ItemMeta of the ItemStack using a provided meta consumer.
     *
     * @param metaConsumer The consumer to modify the ItemMeta.
     * @return The current ItemMetaManager instance.
     */
    @CanIgnoreReturnValue
    public ItemMetaManager meta(@NotNull Consumer<ItemMeta> metaConsumer) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            metaConsumer.accept(meta);
            itemStack.setItemMeta(meta);
        }
        return this;
    }
}