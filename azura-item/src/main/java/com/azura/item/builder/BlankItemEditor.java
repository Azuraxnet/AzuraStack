package com.azura.item.builder;

import com.azura.item.editors.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class BlankItemEditor implements ItemEditor {
    private final ItemBuilder builder;

    BlankItemEditor(ItemBuilder builder) {
        this.builder = builder;
    }

    @Override
    public String getDisplayName() {
        return "";
    }

    @Override
    public ItemEditor setDisplayName(String displayName) {
        return this;
    }

    @Override
    public List<String> getCopyOfLore() {
        return new ArrayList<>();
    }

    @Override
    public ItemEditor setPlaceholder(String placeholder, String value) {
        return this;
    }

    @Override
    public String getTag(Plugin plugin, String key) {
        return "";
    }

    @Override
    public boolean hasTag(Plugin plugin, String key) {
        return false;
    }

    @Override
    public ItemEditor setTag(Plugin plugin, String key, String value) {
        return this;
    }

    @Override
    public ItemEditor removeTag(Plugin plugin, String key) {
        return this;
    }

    @Override
    public ItemEditor addLore(String lore) {
        return this;
    }

    @Override
    public ItemEditor setLore(String... lore) {
        return this;
    }

    @Override
    public ItemEditor setLore(List<String> lore) {
        return this;
    }

    @Override
    public ItemEditor setLore(String lore, String deliminator) {
        return this;
    }

    @Override
    public ItemEditor clearLore() {
        return this;
    }

    @Override
    public ItemEditor replaceLore(int line, String lore) {
        return this;
    }

    @Override
    public ItemMeta getDefaultItemMeta() {
        return null;
    }

    @Override
    public ItemEditor modifyEnchantMeta(Consumer<EnchantEditor> enchantEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor modifyPotionMeta(Consumer<PotionEditor> potionEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor modifySkullMeta(Consumer<SkullEditor> skullEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor modifyLeatherMeta(Consumer<LeatherArmorEditor> leatherArmorEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor modifyFireworkMeta(Consumer<FireworkEditor> fireworkEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor modifyCompassMeta(Consumer<CompassEditor> compassEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor modifyCrossbowMeta(Consumer<CrossbowEditor> crossbowEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor modifyBookMeta(Consumer<BookEditor> bookEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor modifyBannerMeta(Consumer<BannerEditor> bannerEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor modifyMapMeta(Consumer<MapEditor> mapEditorConsumer) {
        return this;
    }

    @Override
    public ItemEditor resetMeta() {
        return this;
    }


    @Override
    public ItemBuilder getBuilder() {
        return builder;
    }

    @Override
    public boolean canEdit() {
        return false;
    }

    @Override
    public int getLoreSize() {
        return 0;
    }

    @Override
    public void save() {

    }

    @Override
    public ItemStack build() {
        return builder.build();
    }
}
