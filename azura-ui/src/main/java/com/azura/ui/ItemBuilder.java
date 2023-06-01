package com.azura.ui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    private ItemStack itemStack;
    public ItemBuilder(Material material){
        itemStack = new ItemStack(material);
    }
    public ItemBuilder(ItemStack itemStack){
        this.itemStack = itemStack;
    }
    public ItemBuilder(String material){
        itemStack = new ItemStack(Material.getMaterial(material));
    }
    public ItemBuilder setAmount(int amount){
        itemStack.setAmount(amount);
        return this;
    }
    public ItemBuilder setDisplayName(String displayName){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(displayName));
        itemMeta.setLore(new ArrayList<>());
        itemStack.setItemMeta(itemMeta);
        return this;
    }
    public ItemBuilder setLore(String lore){
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(lore.contains("/")){
            String[] lorelines = lore.split("/");
            for(int i = 0; i < lorelines.length; i++){
                lorelines[i] = colorize(lorelines[i]);
            }
            itemMeta.setLore(Arrays.asList(lorelines));
        }else {
            itemMeta.setLore(Arrays.asList(colorize(lore)));
        }
        itemStack.setItemMeta(itemMeta);
        return this;
    }
    public ItemBuilder setLore(String... lore){
        ItemMeta itemMeta = itemStack.getItemMeta();
        String[] lorelines = new String[lore.length];
        for(int i = 0; i < lore.length; i++){
            lorelines[i] = colorize(lore[i]);
        }
        itemMeta.setLore(Arrays.asList(lorelines));
        itemStack.setItemMeta(itemMeta);
        return this;
    }
    public ItemBuilder setLore(int line, String lore){
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> itemMetaLore = itemMeta.getLore();
        if(itemMetaLore == null){
            itemMetaLore = new ArrayList<>();
        }
        int max = Math.max(itemMetaLore.size(), line+1);
        List<String> newItemLore = new ArrayList<>();
        for(int i = 0; i < max; i++) {
            if(i == line){
                newItemLore.add(line, ChatColor.translateAlternateColorCodes('&', lore));
            }else if(i > itemMetaLore.size()-1){
                newItemLore.add("");
            }else{
                newItemLore.add(itemMetaLore.get(i));
            }
        }
        itemMeta.setLore(newItemLore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build(){
        return itemStack;
    }
    public static String colorize(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
