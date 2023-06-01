package com.azura.item.editors;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.UUID;

public interface SkullEditor {
    PlayerProfile getPlayerProfile();

    SkullEditor setPlayerProfile(PlayerProfile profile);

    OfflinePlayer getOwner();

    SkullEditor setOwner(OfflinePlayer player);

    SkullMeta getSkullMeta();

    SkullEditor createNewProfile(UUID uuid, String name);

    SkullEditor createNewProfile(UUID uuid, String name, PlayerTextures textures);

    SkullEditor setSkin(URL url);

    SkullEditor setSkin(String textureID);
    SkullEditor setSkinFromBase64(String base64Data);

    boolean hasOwner();
}
