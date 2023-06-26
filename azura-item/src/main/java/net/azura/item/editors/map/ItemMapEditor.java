package net.azura.item.editors.map;

import net.azura.item.editors.ItemEditor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.World;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.ArrayList;
import java.util.List;

public class ItemMapEditor implements MapEditor {
    private final MapMeta meta;
    private MapView mapView;

    public ItemMapEditor(ItemEditor editor) {
        this.meta = (MapMeta) editor.getDefaultItemMeta();
        if (meta.hasMapView()) {
            this.mapView = meta.getMapView();
        } else {
            mapView = null;
        }
    }

    public void save() {
        if (mapView != null) {
            meta.setMapView(mapView);
        }
    }

    @Override
    public boolean hasMapView() {
        return mapView != null;
    }

    @Override
    public MapView getMapView() {
        return mapView;
    }

    @Override
    public MapEditor setMapView(MapView mapView) {
        this.mapView = mapView;
        return this;
    }

    @Override
    public MapEditor createNewMapView(World world) {
        mapView = Bukkit.createMap(world);
        return this;
    }

    @Override
    public MapEditor addRenderer(MapRenderer renderer) {
        if (mapView != null) {
            mapView.addRenderer(renderer);
        }
        return this;
    }

    @Override
    public boolean isScaling() {
        return meta.isScaling();
    }

    @Override
    public MapEditor setColor(Color color) {
        meta.setColor(color);
        return this;
    }

    @Override
    public Color getColor() {
        if (hasColor()) {
            return meta.getColor();
        }
        return Color.BLACK;
    }

    @Override
    public boolean hasLocationName() {
        return meta.hasLocationName();
    }

    @Override
    public boolean hasColor() {
        return meta.hasColor();
    }

    @Override
    public MapEditor setScaleValue(MapView.Scale scale) {
        if (mapView != null) {
            mapView.setScale(scale);
        }
        return this;
    }

    @Override
    public MapView.Scale getScalingValue() {
        if (mapView == null) {
            return MapView.Scale.NORMAL;
        }
        return mapView.getScale();
    }

    @Override
    public MapEditor clearRenders() {
        if (mapView != null) {
            mapView.getRenderers().clear();
        }
        return this;
    }

    @Override
    public MapEditor setScaling(boolean scale) {
        meta.setScaling(scale);
        return this;
    }

    @Override
    public MapEditor setCenterX(int x) {
        if (mapView != null) {
            mapView.setCenterX(x);
        }
        return this;
    }

    @Override
    public int getCenterX() {
        if (mapView != null) {
            return mapView.getCenterX();
        }
        return 0;
    }

    @Override
    public MapEditor setCenterZ(int z) {
        if (mapView != null) {
            mapView.setCenterZ(z);
        }
        return this;
    }

    @Override
    public int getCenterZ() {
        if (mapView != null) {
            return mapView.getCenterZ();
        }
        return 0;
    }

    @Override
    public MapEditor setViewLocked(boolean locked) {
        if (mapView != null) {
            mapView.setLocked(locked);
        }
        return null;
    }

    @Override
    public boolean isViewLocked() {
        if (mapView != null) {
            return mapView.isLocked();
        }
        return false;
    }

    @Override
    public MapEditor showMapMarker(boolean show) {
        if (mapView != null) {
            mapView.setTrackingPosition(show);
        }
        return this;
    }

    @Override
    public MapEditor showMapMarkerOutsideRange(boolean show) {
        if (mapView != null) {
            mapView.setUnlimitedTracking(show);
        }
        return this;
    }

    @Override
    public World getMapWorld() {
        if (mapView != null) {
            return mapView.getWorld();
        }
        return null;
    }

    @Override
    public List<MapRenderer> getRenderers() {
        if (mapView != null) {
            return mapView.getRenderers();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean usingDefaultRenders() {
        if (mapView != null) {
            return mapView.isVirtual();
        }
        return false;
    }

    @Override
    public MapMeta getMapMeta() {
        return meta;
    }

}
