package net.azura.item.editors.crossbow;

import net.azura.item.editors.ItemEditor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCrossbowEditor implements CrossbowEditor {
    private final CrossbowMeta meta;

    public ItemCrossbowEditor(ItemEditor editor) {
        this.meta = (CrossbowMeta) editor.getDefaultItemMeta();
    }

    @Override
    public CrossbowEditor addProjectile(ItemStack projectile) {
        meta.addChargedProjectile(projectile);
        return this;
    }

    @Override
    public CrossbowEditor addProjectiles(ItemStack... projectiles) {
        for (ItemStack projectile : projectiles) {
            addProjectile(projectile);
        }
        return this;
    }

    @Override
    public CrossbowEditor setProjectiles(List<ItemStack> projectiles) {
        meta.setChargedProjectiles(projectiles);
        return this;
    }

    @Override
    public CrossbowEditor removeProjectile(ItemStack projectile) {
        List<ItemStack> current = getProjectiles();
        List<ItemStack> replace = new ArrayList<>();
        for (ItemStack i : current) {
            if (!i.isSimilar(projectile)) {
                replace.add(i);
            }
        }
        setProjectiles(replace);
        return this;
    }

    @Override
    public CrossbowEditor removeProjectilesByType(Material material) {
        List<ItemStack> current = getProjectiles();
        List<ItemStack> replace = new ArrayList<>();
        for (ItemStack i : current) {
            if (i.getType() != material) {
                replace.add(i);
            }
        }
        setProjectiles(replace);
        return this;
    }

    @Override
    public CrossbowEditor clearProjectiles() {
        setProjectiles(new ArrayList<>());
        return this;
    }

    @Override
    public boolean hasProjectiles() {
        return meta.hasChargedProjectiles();
    }

    @Override
    public List<ItemStack> getProjectiles() {
        return meta.getChargedProjectiles();
    }

}
