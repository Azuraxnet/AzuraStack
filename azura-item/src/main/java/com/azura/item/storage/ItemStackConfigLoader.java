package com.azura.item.storage;

import com.azura.item.builder.ItemBuilder;
import com.azura.item.editors.ItemEditor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ItemStackConfigLoader {
    private String materialSection = "material";
    private String displayNameSection = "display-name";
    private String loreSection = "lore";
    private String enchantmentSection = "enchantments";
    private String tagSection = "tags";

    public void setMaterialSection(String materialSection) {
        this.materialSection = materialSection;
    }

    public void setDisplayNameSection(String displayNameSection) {
        this.displayNameSection = displayNameSection;
    }

    public void setLoreSection(String loreSection) {
        this.loreSection = loreSection;
    }

    public void setEnchantmentSection(String enchantmentSection) {
        this.enchantmentSection = enchantmentSection;
    }

    public void setTagSection(String tagSection) {
        this.tagSection = tagSection;
    }
    /*
    someSection:
      material: DIAMOND_SWORD
      display-name: "&bTEST"
      lore:
        - "&cA"
        - "&aB"
      enchantments:
        SHARPNESS: 4
      tags:
        L2: "BLAH"
     */

    public ItemStack readFromConfig(ConfigurationSection section) {
        String material = section.getString(materialSection);
        ItemEditor editor = new ItemBuilder(material).getEditor();
        setDisplayName(section, editor);
        setLore(section, editor);
        setEnchantments(section, editor);
        return editor.build();
    }

    public ItemStack readFromConfigWithTags(Plugin plugin, ConfigurationSection section) {
        String material = section.getString(materialSection);
        ItemEditor editor = new ItemBuilder(material).getEditor();
        setDisplayName(section, editor);
        setLore(section, editor);
        setEnchantments(section, editor);
        setTags(plugin, section, editor);
        return editor.build();
    }

    private void setDisplayName(ConfigurationSection section, ItemEditor editor) {
        String displayName = section.getString(displayNameSection);
        if (displayName != null) {
            editor.setDisplayName(displayName);
        }
    }

    private void setLore(ConfigurationSection section, ItemEditor editor) {
        List<String> loreLines = section.getStringList(loreSection);
        if (!loreLines.isEmpty()) {
            editor.setLore(loreLines);
        }
    }

    private void setEnchantments(ConfigurationSection section, ItemEditor editor) {
        ConfigurationSection enchants = section.getConfigurationSection(enchantmentSection);
        if (enchants == null) return;
        editor.modifyEnchantMeta(enchantEditor -> {
            Enchantment[] earr = Enchantment.values();
            for (String enc : enchants.getKeys(true)) {
                int value = enchants.getInt(enc);
                for (Enchantment e : earr) {
                    if (e.getKey().getKey().equalsIgnoreCase(enc)) {
                        enchantEditor.addEnchantment(e, value);
                    }
                }
            }
        });
    }

    private void setTags(Plugin plugin, ConfigurationSection section, ItemEditor editor) {
        ConfigurationSection tags = section.getConfigurationSection(tagSection);
        if (tags == null) return;
        for (String tag : tags.getKeys(true)) {
            String value = tags.getString(tag);
            editor.setTag(plugin, tag, value);
        }
    }
}
