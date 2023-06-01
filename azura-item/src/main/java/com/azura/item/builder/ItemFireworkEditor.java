package com.azura.item.builder;

import com.azura.item.editors.FireworkEditor;
import com.azura.item.editors.ItemEditor;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Collections;
import java.util.List;

public class ItemFireworkEditor implements FireworkEditor {
    private final FireworkMeta meta;

    ItemFireworkEditor(ItemEditor editor) {
        this.meta = (FireworkMeta) editor.getDefaultItemMeta();
    }

    @Override
    public FireworkEditor addEffect(FireworkEffect effect) {
        meta.addEffect(effect);
        return this;
    }

    @Override
    public FireworkEditor addEffects(FireworkEffect... effects) {
        meta.addEffects(effects);
        return this;
    }

    @Override
    public FireworkEditor addEffects(List<FireworkEffect> effects) {
        meta.addEffects(effects);
        return this;
    }

    @Override
    public FireworkEditor clearEffects() {
        meta.clearEffects();
        return this;
    }

    @Override
    public FireworkEditor setFireworkRange(int power) {
        meta.setPower(power);
        return this;
    }

    @Override
    public FireworkEditor removeEffect(FireworkEffect effect) {
        int index = getEffects().indexOf(effect);
        if (index != -1) {
            removeEffect(index);
        }
        return this;
    }


    @Override
    public FireworkEditor removeEffect(int index) {
        meta.removeEffect(index);
        return null;
    }

    @Override
    public FireworkEditor removeEffectsByType(FireworkEffect.Type type) {
        List<FireworkEffect> effects = getEffects();
        for (int i = 0; i < effects.size(); i++) {
            if (effects.get(i).getType() == type) {
                removeEffect(i);
            }
        }
        return this;
    }


    @Override
    public FireworkMeta getFireworkMeta() {
        return meta;
    }

    @Override
    public List<FireworkEffect> getEffects() {
        return Collections.unmodifiableList(meta.getEffects());
    }

    @Override
    public int getFireworkRange() {
        return meta.getPower();
    }

    @Override
    public int getEffectsAmount() {
        return meta.getEffectsSize();
    }
}

