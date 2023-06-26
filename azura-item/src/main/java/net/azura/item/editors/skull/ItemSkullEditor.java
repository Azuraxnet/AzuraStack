package net.azura.item.editors.skull;

import net.azura.item.editors.ItemEditor;
import net.azura.item.editors.skull.SkullEditor;
import net.azura.item.exception.MalformedSkinDataException;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

public class ItemSkullEditor implements SkullEditor {
    private final SkullMeta meta;
    private static final String prefix = "{\"textures\":{\"SKIN\":{\"url\":\"";
    private static final String urlPrefix = "http://textures.minecraft.net/texture/";
    private static final String suffix = "\"}}}";


    public ItemSkullEditor(ItemEditor editor) {
        this.meta = (SkullMeta) editor.getDefaultItemMeta();
    }

    @Override
    public PlayerProfile getPlayerProfile() {
        return meta.getOwnerProfile();
    }

    @Override
    public OfflinePlayer getOwner() {
        return meta.getOwningPlayer();
    }

    @Override
    public SkullMeta getSkullMeta() {
        return meta;
    }

    @Override
    public SkullEditor setPlayerProfile(PlayerProfile profile) {
        meta.setOwnerProfile(profile);
        return this;
    }

    @Override
    public SkullEditor createNewProfile(UUID uuid, String name) {
        PlayerProfile profile = Bukkit.getServer().createPlayerProfile(uuid, name);
        setPlayerProfile(profile);
        return this;
    }

    @Override
    public SkullEditor createNewProfile(UUID uuid, String name, PlayerTextures textures) {
        createNewProfile(uuid, name);
        getPlayerProfile().setTextures(textures);
        return this;
    }
    public SkullEditor setSkin(String textureID){
        String data = prefix + urlPrefix + textureID + suffix;
        byte[] b = Base64.getEncoder().encode(data.getBytes());
        return setSkinFromBase64(new String(b));
    }
    public SkullEditor setSkin(URL url) {
        String s = prefix+ url.toString() + suffix;
        byte[] b =Base64.getEncoder().encode(s.getBytes());
        return setSkinFromBase64(new String(b));
    }

    public SkullEditor setSkinFromBase64(String base64Data){
        if(!validateSkinData(base64Data)) return this;
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64Data));
        try{
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        }catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }
    private boolean validateSkinData(String data){
        byte[] bytes = Base64.getDecoder().decode(data);
        String decodedData = new String(bytes);
        try {
            if (!decodedData.contains(prefix) || !decodedData.contains(suffix)) {
                throw new MalformedSkinDataException(decodedData + " is not formatted properly. Please check the skin data!");
            }else if(!decodedData.contains(urlPrefix)) {
                throw new MalformedSkinDataException(decodedData + " is not formatted properly. Skin is required to be pulled from http://textures.minecraft.net/texture/");
            }
            return true;
        }catch (MalformedSkinDataException e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public SkullEditor setOwner(OfflinePlayer player) {
        meta.setOwningPlayer(player);
        return this;
    }

    @Override
    public boolean hasOwner() {
        return meta.hasOwner();
    }

}

