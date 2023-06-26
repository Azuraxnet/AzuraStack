package net.azura.item.editors.enchants;

import net.azura.item.editors.ItemEditor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class ItemEnchantEditor implements EnchantEditor {
    private final ItemMeta meta;
    private boolean allowUnsafe = false;

    public ItemEnchantEditor(ItemEditor editor) {
        this.meta = editor.getDefaultItemMeta();

    }

    @Override
    public boolean canAddEnchantment(Enchantment enchantment) {
        if (allowUnsafe) {
            return true;
        }
        return meta.hasConflictingEnchant(enchantment);
    }

    @Override
    public boolean hasEnchantment(Enchantment enchantment) {
        return meta.hasEnchant(enchantment);
    }

    @Override
    public int getEnchantmentLevel(Enchantment enchantment) {
        return meta.getEnchantLevel(enchantment);
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        return meta.getEnchants();
    }

    @Override
    public EnchantEditor allowUnsafeEnchantments(boolean allow) {
        this.allowUnsafe = allow;
        if (!allow) {
            removeUnsafeEnchantments();
        }
        return this;
    }

    @Override
    public EnchantEditor addEnchantment(Enchantment enchantment) {
        meta.addEnchant(enchantment, enchantment.getStartLevel(), allowUnsafe);
        return this;
    }

    @Override
    public EnchantEditor addEnchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, allowUnsafe);
        return this;
    }

    @Override
    public EnchantEditor removeEnchantment(Enchantment enchantment) {
        if (meta.hasEnchant(enchantment)) {
            meta.removeEnchant(enchantment);
        }
        return this;
    }

    @Override
    public EnchantEditor clearEnchantments() {
        Map<Enchantment, Integer> map = getEnchantments();
        for (Enchantment e : map.keySet()) {
            meta.removeEnchant(e);
        }
        return this;
    }

    @Override
    public EnchantEditor setHideEnchantments(boolean hide) {
        if (hide) {
            if (!meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            } else {
                if (meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
                    meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
            }
        }
        return this;
    }

    @Override
    public EnchantEditor removeUnsafeEnchantments() {
        Map<Enchantment, Integer> map = getEnchantments();
        for (Enchantment e : map.keySet()) {
            int level = map.get(e);
            if (meta.hasConflictingEnchant(e)) {
                meta.removeEnchant(e);
                continue;
            }
            if (level > e.getMaxLevel()) {
                meta.removeEnchant(e);
                meta.addEnchant(e, e.getMaxLevel(), false);
            }
        }
        return this;
    }
}

