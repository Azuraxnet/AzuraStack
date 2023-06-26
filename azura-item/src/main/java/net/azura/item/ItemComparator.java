package net.azura.item;

import net.azura.item.builder.ItemMetaEditor;
import net.azura.item.editors.ItemEditor;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public final class ItemComparator {
    public static boolean hasDisplayName(ItemStack itemStack, String displayName) {
        ItemEditor editor = new ItemMetaEditor(itemStack);
        if (!editor.canEdit()) return false;
        String display = editor.getDisplayName();
        return ChatColor.stripColor(display).equalsIgnoreCase(displayName);
    }

    public static boolean hasLore(ItemStack itemStack, String lore) {
        ItemEditor editor = new ItemMetaEditor(itemStack);
        if (!editor.canEdit()) return false;
        List<String> itemLore = editor.getCopyOfLore();
        for (String s : itemLore) {
            if (ChatColor.stripColor(s).contains(lore)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasLoreAt(ItemStack itemStack, int index, String lore) {
        ItemEditor editor = new ItemMetaEditor(itemStack);
        if (!editor.canEdit()) return false;
        List<String> itemLore = editor.getCopyOfLore();
        if (index >= itemLore.size()) return false;
        return ChatColor.stripColor(itemLore.get(index)).contains(lore);
    }

    public static boolean hasTag(Plugin plugin, ItemStack itemStack, String key) {
        ItemEditor editor = new ItemMetaEditor(itemStack);
        if (!editor.canEdit()) return false;
        return editor.hasTag(plugin, key);
    }

    /*
static boolean same(ItemStack itemStack1, ItemStack itemStack2) {
    if (itemStack1 != null && itemStack2 != null) {
        if (itemStack1.hasItemMeta() && itemStack2.hasItemMeta()) {
            if (!Objects.equals(itemStack1.getDefaultItemMeta(), itemStack2.getDefaultItemMeta())) {
                return false;
            }
        } else if (itemStack1.hasItemMeta() || itemStack2.hasItemMeta()) {
            return false;
        }
        if (itemStack1.getType() != itemStack2.getType()) {
            return false;
        }
        return itemStack1.getEnchantments().equals(itemStack2.getEnchantments());
    } else {
        return itemStack1 == null && itemStack2 == null;
    }
}
 */
    public static boolean isSame(ItemStack itemStack, ItemStack itemStack2) {
        return itemStack.isSimilar(itemStack2);
    }
}
