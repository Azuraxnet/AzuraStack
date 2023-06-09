package com.azura.item.editors;

import com.azura.item.builder.ItemBuilder;
import com.azura.item.builder.ItemMetaEditor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.function.Consumer;

public interface ItemEditor extends Editor {
    static ItemEditor createEditor(ItemStack itemStack) {
        return new ItemMetaEditor(itemStack);
    }

    String getDisplayName();

    ItemEditor setDisplayName(String displayName);

    List<String> getCopyOfLore();

    ItemEditor setPlaceholder(String placeholder, String value);

    String getTag(Plugin plugin, String key);

    boolean hasTag(Plugin plugin, String key);

    ItemEditor setTag(Plugin plugin, String key, String value);

    ItemEditor removeTag(Plugin plugin, String key);

    ItemEditor addLore(String lore);

    ItemEditor setLore(String... lore);

    ItemEditor setLore(List<String> lore);

    ItemEditor setLore(String lore, String deliminator);

    ItemEditor clearLore();

    ItemEditor replaceLore(int line, String lore);

    ItemMeta getDefaultItemMeta();

    ItemEditor modifyEnchantMeta(Consumer<EnchantEditor> enchantEditorConsumer);

    ItemEditor modifyPotionMeta(Consumer<PotionEditor> potionEditorConsumer);

    ItemEditor modifySkullMeta(Consumer<SkullEditor> skullEditorConsumer);

    ItemEditor modifyLeatherMeta(Consumer<LeatherArmorEditor> leatherArmorEditorConsumer);

    ItemEditor modifyFireworkMeta(Consumer<FireworkEditor> fireworkEditorConsumer);

    ItemEditor modifyCompassMeta(Consumer<CompassEditor> compassEditorConsumer);

    ItemEditor modifyCrossbowMeta(Consumer<CrossbowEditor> crossbowEditorConsumer);

    ItemEditor modifyBookMeta(Consumer<BookEditor> bookEditorConsumer);

    ItemEditor modifyBannerMeta(Consumer<BannerEditor> bannerEditorConsumer);

    ItemEditor modifyMapMeta(Consumer<MapEditor> mapEditorConsumer);

    ItemEditor resetMeta();

    ItemBuilder getBuilder();

    boolean canEdit();


    int getLoreSize();
}
