package net.azura.item.editors.potion;

import org.bukkit.Color;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public interface PotionEditor {
    PotionEditor clearEffects();

    PotionEditor addPotionEffect(PotionEffect potionEffect);

    PotionEditor addPotionEffect(PotionEffect potionEffect, boolean overwrite);

    PotionEditor setColor(Color color);

    Color getColor(Color color);

    PotionEditor removePotionEffect(PotionEffect potionEffect);

    PotionEditor removeAllPotionEffectsByType(PotionEffectType type);

    List<PotionEffect> getPotionEffects();

    PotionMeta getPotionMeta();
}
