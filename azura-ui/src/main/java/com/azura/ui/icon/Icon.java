package com.azura.ui.icon;

import com.azura.ui.actions.Actionable;
import org.bukkit.inventory.ItemStack;

public interface Icon extends Actionable {
    ItemStack getRepresentation();
    void update(ItemStack itemStack);
}
