package net.azura.item.editors.leather;

import net.azura.item.editors.ItemEditor;
import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemLeatherArmorEditor implements LeatherArmorEditor {
    private final LeatherArmorMeta meta;

    public ItemLeatherArmorEditor(ItemEditor editor) {
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
