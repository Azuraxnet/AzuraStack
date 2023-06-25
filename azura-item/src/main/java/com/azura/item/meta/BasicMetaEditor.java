package com.azura.item.meta;

import org.apache.commons.lang.StringUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.util.List;

public class BasicMetaEditor<T extends MetaEditor<T,M>, M extends ItemMeta> implements MetaEditor<T, M>{
    private T editor;
    private M meta;
    public BasicMetaEditor(T editor, M meta){
        this.editor = editor;
        this.meta = meta;
    }

    @Override
    public String getItemName() {
        return "";
    }

    @Override
    public T setTag(@Nonnull Plugin plugin, @Nonnull String key, @Nonnull String value) {
        return null;
    }

    @Override
    public T clearTags(@Nonnull Plugin plugin, @Nonnull String key) {
        return null;
    }

    @Override
    public String getTag(@Nonnull Plugin plugin, @Nonnull String key) {
        return null;
    }

    @Override
    public T addLoreLine(String lore) {
        return null;
    }

    @Override
    public T setLore(String... lore) {
        return null;
    }

    @Override
    public T setLore(List<String> lore) {
        return null;
    }

    @Override
    public T clearLore() {
        return null;
    }

    @Override
    public T setLore(int line, String lore) {
        return null;
    }

    @Override
    public String getLore(int line) {
        return null;
    }

    @Override
    public List<String> getLore() {
        return null;
    }

    @Override
    public int getLoreSize() {
        return 0;
    }

    @Override
    public T setDisplayName(String displayName) {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public ItemStack build() {
        return null;
    }
}
