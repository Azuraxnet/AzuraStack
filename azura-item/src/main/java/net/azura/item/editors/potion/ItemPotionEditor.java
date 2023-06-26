package net.azura.item.editors.potion;

import net.azura.item.editors.ItemEditor;
import net.azura.item.editors.potion.PotionEditor;
import org.bukkit.Color;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class ItemPotionEditor implements PotionEditor {
    public final PotionMeta meta;
    public ItemPotionEditor(ItemEditor editor) {
        this.meta = (PotionMeta) editor.getDefaultItemMeta();
    }

    @Override
    public PotionEditor clearEffects() {
        meta.clearCustomEffects();
        return this;
    }

    @Override
    public PotionEditor addPotionEffect(PotionEffect potionEffect) {
        meta.addCustomEffect(potionEffect, true);
        return this;
    }

    @Override
    public PotionEditor addPotionEffect(PotionEffect potionEffect, boolean overwrite) {
        meta.addCustomEffect(potionEffect, overwrite);
        return this;
    }

    @Override
    public PotionEditor setColor(Color color) {
        meta.setColor(color);
        return this;
    }

    @Override
    public Color getColor(Color color) {
        return meta.getColor();
    }

    @Override
    public PotionEditor removePotionEffect(PotionEffect potionEffect) {
        List<PotionEffect> effects = meta.getCustomEffects();
        List<PotionEffect> toAdd = meta.getCustomEffects();
        for (PotionEffect effect : effects) {
            if (!samePotion(effect, potionEffect)) {
                toAdd.add(effect);
            }
        }
        meta.clearCustomEffects();
        for (PotionEffect add : toAdd) {
            meta.addCustomEffect(add, false);
        }
        return this;
    }

    private boolean samePotion(PotionEffect a, PotionEffect b) {
        if (!a.getType().equals(b.getType())) {
            return false;
        }
        if (a.getAmplifier() != b.getAmplifier()) {
            return false;
        }
        return a.getDuration() == b.getDuration();
    }

    @Override
    public PotionEditor removeAllPotionEffectsByType(PotionEffectType type) {
        meta.removeCustomEffect(type);
        return this;
    }

    @Override
    public List<PotionEffect> getPotionEffects() {
        return meta.getCustomEffects();
    }

    @Override
    public PotionMeta getPotionMeta() {
        return meta;
    }

}

