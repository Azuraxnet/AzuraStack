package net.azura.item.editors.map;

import org.bukkit.Color;
import org.bukkit.World;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.List;

public interface MapEditor {
    boolean hasMapView();

    boolean isScaling();

    MapEditor setScaling(boolean scale);

    MapView getMapView();

    MapEditor setMapView(MapView mapView);

    MapEditor createNewMapView(World world);

    MapEditor addRenderer(MapRenderer renderer);

    Color getColor();

    MapEditor setColor(Color color);

    boolean hasLocationName();

    boolean hasColor();

    //MapView
    MapEditor setScaleValue(MapView.Scale scale);

    MapView.Scale getScalingValue();

    MapEditor clearRenders();

    int getCenterX();

    MapEditor setCenterX(int x);

    int getCenterZ();

    MapEditor setCenterZ(int z);

    boolean isViewLocked();

    MapEditor setViewLocked(boolean locked);

    MapEditor showMapMarker(boolean show);

    MapEditor showMapMarkerOutsideRange(boolean show);

    World getMapWorld();

    List<MapRenderer> getRenderers();

    boolean usingDefaultRenders();

    MapMeta getMapMeta();

}
