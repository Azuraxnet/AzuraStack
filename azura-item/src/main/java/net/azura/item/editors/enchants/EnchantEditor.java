package net.azura.item.editors.enchants;

import org.bukkit.enchantments.Enchantment;

import java.util.Map;

public interface EnchantEditor {
    boolean canAddEnchantment(Enchantment enchantment);

    boolean hasEnchantment(Enchantment enchantment);

    int getEnchantmentLevel(Enchantment enchantment);

    Map<Enchantment, Integer> getEnchantments();

    EnchantEditor allowUnsafeEnchantments(boolean allow);

    EnchantEditor addEnchantment(Enchantment enchantment);

    EnchantEditor addEnchantment(Enchantment enchantment, int level);

    EnchantEditor removeEnchantment(Enchantment enchantment);

    EnchantEditor clearEnchantments();

    EnchantEditor setHideEnchantments(boolean hide);

    EnchantEditor removeUnsafeEnchantments();
}
