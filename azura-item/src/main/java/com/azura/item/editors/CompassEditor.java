package com.azura.item.editors;

import org.bukkit.Location;

public interface CompassEditor {
    CompassEditor enableTracking(boolean tracking);

    boolean isTracking();

    Location getDirection();

    CompassEditor setDirection(Location location);

    boolean hasDirection();
}
