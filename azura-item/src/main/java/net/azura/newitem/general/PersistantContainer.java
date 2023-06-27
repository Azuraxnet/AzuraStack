package net.azura.newitem.general;

import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;

public interface PersistantContainer {
    void setTag(@Nonnull Plugin plugin, @Nonnull String key, @Nonnull String value);
    void clearTags(@Nonnull Plugin plugin, @Nonnull String key);
    String getTag(@Nonnull Plugin plugin, @Nonnull String key);
}
