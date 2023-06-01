package com.azura.item.editors;

import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public interface LeatherArmorEditor {
    Color getColor();

    LeatherArmorEditor setColor(Color color);

    LeatherArmorMeta getLeatherArmorMeta();
}
