package com.azura.item.builder;

import com.azura.item.editors.ItemEditor;
import com.azura.item.editors.LeatherArmorEditor;
import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemLeatherArmorEditor implements LeatherArmorEditor {
    private final LeatherArmorMeta meta;

    ItemLeatherArmorEditor(ItemEditor editor) {
        this.meta = (LeatherArmorMeta) editor.getDefaultItemMeta();
    }

    @Override
    public LeatherArmorEditor setColor(Color color) {
        meta.setColor(color);
        return this;
    }

    @Override
    public Color getColor() {
        return meta.getColor();
    }

    @Override
    public LeatherArmorMeta getLeatherArmorMeta() {
        return meta;
    }

}
