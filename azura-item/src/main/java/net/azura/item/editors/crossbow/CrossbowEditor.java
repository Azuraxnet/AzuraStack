package net.azura.item.editors.crossbow;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface CrossbowEditor {
    CrossbowEditor addProjectile(ItemStack projectile);

    CrossbowEditor addProjectiles(ItemStack... projectiles);

    CrossbowEditor removeProjectile(ItemStack projectile);

    CrossbowEditor removeProjectilesByType(Material material);

    CrossbowEditor clearProjectiles();

    boolean hasProjectiles();

    List<ItemStack> getProjectiles();

    CrossbowEditor setProjectiles(List<ItemStack> projectiles);
}
