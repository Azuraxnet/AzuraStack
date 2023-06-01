package com.azura.item.editors;

import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.List;

public interface FireworkEditor {
    FireworkEditor addEffect(FireworkEffect effect);

    FireworkEditor addEffects(FireworkEffect... effects);

    FireworkEditor addEffects(List<FireworkEffect> effects);

    FireworkEditor clearEffects();

    FireworkEditor removeEffect(FireworkEffect effect);

    FireworkEditor removeEffect(int index);

    FireworkEditor removeEffectsByType(FireworkEffect.Type type);

    FireworkMeta getFireworkMeta();

    List<FireworkEffect> getEffects();

    int getFireworkRange();

    FireworkEditor setFireworkRange(int power);

    int getEffectsAmount();

}
