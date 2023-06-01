package com.azura.item.builder;

import com.azura.item.editors.CompassEditor;
import com.azura.item.editors.ItemEditor;
import org.bukkit.Location;
import org.bukkit.inventory.meta.CompassMeta;

public class ItemCompassEditor implements CompassEditor {
    private final CompassMeta meta;

    ItemCompassEditor(ItemEditor editor) {
        this.meta = (CompassMeta) editor.getDefaultItemMeta();
    }


    @Override
    public CompassEditor setDirection(Location location) {
        meta.setLodestone(location);
        return this;
    }

    @Override
    public CompassEditor enableTracking(boolean tracking) {
        meta.setLodestoneTracked(true);
        return this;
    }

    @Override
    public boolean isTracking() {
        return meta.isLodestoneTracked();
    }

    @Override
    public Location getDirection() {
        return meta.getLodestone();
    }

    @Override
    public boolean hasDirection() {
        return meta.hasLodestone();
    }

}
