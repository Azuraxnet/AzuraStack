package net.azura.item.editors;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.util.List;

public interface MetaEditor<T extends MetaEditor<T, M>, M extends ItemMeta> extends Cloneable{

    /* General */
    String getItemName();

    /* Tags */
    T setTag(@Nonnull Plugin plugin, @Nonnull String key, @Nonnull String value);
    T clearTags(@Nonnull Plugin plugin, @Nonnull String key);
    String getTag(@Nonnull Plugin plugin, @Nonnull String key);

    /* Lore */
    T addLoreLine(String lore);
    T setLore(String... lore);

    T setLore(List<String> lore);
    T clearLore();
    T setLore(int line, String lore);
    String getLore(int line);
    List<String> getLore();
    int getLoreSize();

    /* Display Name */
    T setDisplayName(String displayName);
    String getDisplayName();
    /* API specific */
    ItemStack build();
}
