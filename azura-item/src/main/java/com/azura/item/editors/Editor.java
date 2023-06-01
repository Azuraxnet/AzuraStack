package com.azura.item.editors;

import org.bukkit.inventory.ItemStack;

public interface Editor {
    void save();

    ItemStack build();
}
