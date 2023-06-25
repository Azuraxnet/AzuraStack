package com.azura.item.builder;

import com.azura.item.editors.*;
import com.azura.item.exception.MetaDataUnavailableException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;

public class ItemMetaEditor implements ItemEditor, Editor {
    private final ItemStack itemStack;
    private ItemMeta itemMeta;
    private final boolean canUse;
    private ItemBuilder builder;
    private Map<String, String> placeholders;

    public ItemMetaEditor(ItemStack stack) {

        this.itemStack = stack;
        if (itemStack.hasItemMeta()) {
            itemMeta = itemStack.getItemMeta();
            canUse = true;
            return;
        }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            canUse = false;
            itemMeta = null;
        } else {
            this.itemMeta = meta;
            canUse = true;
        }
        builder = new ItemBuilder(this, stack);
        placeholders = new HashMap<>();
    }

    ItemMetaEditor(ItemBuilder builder, ItemStack itemStack) {
        this(itemStack);
        this.builder = builder;
    }

    @Override
    public boolean canEdit() {
        return canUse;
    }

    @Override
    public String getDisplayName() {
        if (isMetaDataInvalid("Defaulting to item name instead.")) {
            return getItemName();
        }
        if (itemMeta.hasDisplayName()) {
            return itemMeta.getDisplayName();
        } else {
            return getItemName();
        }
    }

    @Override
    public ItemEditor setPlaceholder(@Nonnull String placeholder, @Nonnull String value) {
        placeholders.put(placeholder, value);
        return this;
    }

    @Nullable
    @Override
    public String getTag(@Nonnull Plugin plugin, @Nonnull String key) {
        if (isMetaDataInvalid("Unable to grab meta tag")) return null;
        if (hasTag(plugin, key)) {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            return container.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    @Override
    public boolean hasTag(@Nonnull Plugin plugin, @Nonnull String key) {
        if (isMetaDataInvalid("Unable to grab meta tag")) return false;
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(namespacedKey, PersistentDataType.STRING);
    }

    @Override
    public ItemEditor setTag(@Nonnull Plugin plugin, @Nonnull String key, @Nonnull String value) {
        if (isMetaDataInvalid("Unable to set meta tag")) return this;
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        removeTag(plugin, key);
        container.set(namespacedKey, PersistentDataType.STRING, value);
        return this;
    }

    @Override
    public ItemEditor removeTag(@Nonnull Plugin plugin, @Nonnull String key) {
        if (isMetaDataInvalid("Unable to remove meta tag")) return this;
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if (hasTag(plugin, key)) {
            container.remove(namespacedKey);
        }
        return this;
    }

    private String getItemName() {
        String name = itemStack.getType().name().toLowerCase();
        String[] nameSplit = name.split("_");
        StringBuilder builder = new StringBuilder();
        for (String word : nameSplit) {
            String s = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            builder.append(s);
        }
        return builder.toString();
    }

    @Override
    public List<String> getCopyOfLore() {
        if (isMetaDataInvalid("Defaulting to empty list instead.")) {
            return Collections.unmodifiableList(new ArrayList<>());
        }
        return Collections.unmodifiableList(getLoreOrDefault());
    }

    @Override
    public ItemEditor setDisplayName(String displayName) {
        if (isMetaDataInvalid("Unable to set the Display Name.")) {
            return this;
        }
        if (displayName == null) {
            itemMeta.setDisplayName("");
            return this;
        }
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        return this;
    }

    @Override
    public ItemEditor addLore(String lore) {
        if (isMetaDataInvalid("Unable to add lore.")) {
            return this;
        }
        if (lore == null) {
            itemMeta.setLore(new ArrayList<>());
            return this;
        }
        List<String> lines = getLoreOrDefault();
        lines.add(ChatColor.translateAlternateColorCodes('&', lore));
        itemMeta.setLore(lines);
        return this;
    }

    @Override
    public ItemEditor setLore(String... lore) {
        if (isMetaDataInvalid("Unable to set the lore.")) {
            return this;
        }
        if (lore == null) {
            itemMeta.setLore(new ArrayList<>());
            return this;
        }
        List<String> lines = getLoreOrDefault();
        for (int i = 0; i < lore.length; i++) {
            if (i < lines.size()) {
                lines.set(i, ChatColor.translateAlternateColorCodes('&', lore[i]));
            } else {
                lines.add(ChatColor.translateAlternateColorCodes('&', lore[i]));
            }
        }
        itemMeta.setLore(lines);
        return this;
    }

    @Override
    public ItemEditor setLore(List<String> lore) {
        if(isMetaDataInvalid("Unable to set the lore.")){
            return this;
        }
        if(lore.isEmpty()){
            clearLore();
        }
        for(int i = 0; i < lore.size(); i++){
            lore.set(i, ChatColor.translateAlternateColorCodes('&',lore.get(i)));
        }
        itemMeta.setLore(lore);
        return this;
    }

    @Override
    public ItemEditor clearLore() {
        if(isMetaDataInvalid("Unable to clear the lore")){
            return this;
        }
        itemMeta.setLore(new ArrayList<>());
        return this;
    }

    @Override
    public ItemEditor setLore(String lore, String deliminator) {
        ArrayList<String> lines = new ArrayList<>();
        if(!lore.contains(deliminator)){
            lines.add(ChatColor.translateAlternateColorCodes('&',lore));
        }else{
            String[] strs = lore.split(deliminator);
            for(int i = 0; i < strs.length; i++){
                lines.add(ChatColor.translateAlternateColorCodes('&', strs[i]));
            }
        }
        itemMeta.setLore(lines);
        return this;
    }

    @Override
    public ItemEditor replaceLore(int line, String lore) {
        if (isMetaDataInvalid("Unable to replace the lore.")) {
            return this;
        }
        List<String> lines = getLoreOrDefault();
        if (line < lines.size()) {
            if (lore == null) {
                lines.set(line, "");
            } else {
                lines.set(line, ChatColor.translateAlternateColorCodes('&', lore));
            }
        } else {
            for (int i = 0; i < line - lines.size(); i++) {
                lines.add("");
            }
            if (lore == null) {
                lines.add("");
            } else {
                lines.add(ChatColor.translateAlternateColorCodes('&', lore));
            }
        }
        itemMeta.setLore(lines);
        return this;
    }

    private List<String> getLoreOrDefault() {
        if (canUse && itemMeta.hasLore()) {
            return itemMeta.getLore();
        }
        itemMeta.setLore(new ArrayList<>());
        if (itemMeta.getLore() == null) {
            return new ArrayList<>();
        }
        return itemMeta.getLore();
    }

    @Override
    public int getLoreSize() {
        if (isMetaDataInvalid("Defaulting to 0 instead.")) {
            return 0;
        }
        return getLoreOrDefault().size();
    }

    @Override
    public @Nullable ItemMeta getDefaultItemMeta() {
        return itemMeta;
    }

    @Override
    public ItemEditor modifyEnchantMeta(Consumer<EnchantEditor> enchantEditorConsumer) {
        if(!isMetaDataInvalid("Unable to modify enchantments")){
            enchantEditorConsumer.accept(new ItemEnchantEditor(this));
        }
        return this;
    }



    /*

        ***********************************************************************************************


        Instead of having a method for each meta types. We should use a generic method

public <T extends MetaBuilder> T getMetaBuilder(Class<T> metaClass) {
    ItemMeta meta = item.getItemMeta();
    if (metaClass.isInstance(meta)) {
        try {
            Constructor<T> constructor = metaClass.getConstructor(ItemMeta.class);
            return constructor.newInstance(meta);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Unable to retrieve meta", e);
        }
    }
    throw new IllegalStateException("This ItemStack does not have " + metaClass.getSimpleName());
}

public <T extends MetaEditor<T>> ItemEditor usingEditor(T editorInstance, Consumer<T> editor) {
    editor.accept(editorInstance);
    return this;
}

     */
    public <T> ItemEditor usingEditor(Consumer<T> editor) {
        T t; //Create the T class instance, if it can't be casted we return this.
        //apply
        editor.accept(t);
        return this;
    }








    @Override
    public ItemEditor modifyPotionMeta(Consumer<PotionEditor> potionEditorConsumer) {
        if (getDefaultItemMeta() instanceof PotionMeta) {
            potionEditorConsumer.accept(new ItemPotionEditor(this));
        }
        return this;
    }

    @Override
    public ItemEditor modifySkullMeta(Consumer<SkullEditor> skullEditorConsumer) {
        if (getDefaultItemMeta() instanceof SkullMeta) {
            skullEditorConsumer.accept(new ItemSkullEditor(this));
        }
        return this;
    }

    @Override
    public ItemEditor modifyLeatherMeta(Consumer<LeatherArmorEditor> leatherArmorEditorConsumer) {
        if (getDefaultItemMeta() instanceof LeatherArmorMeta) {
            leatherArmorEditorConsumer.accept(new ItemLeatherArmorEditor(this));
        }
        return this;
    }

    @Override
    public ItemEditor modifyFireworkMeta(Consumer<FireworkEditor> fireworkEditorConsumer) {
        if (getDefaultItemMeta() instanceof FireworkMeta) {
            fireworkEditorConsumer.accept(new ItemFireworkEditor(this));
        }
        return this;
    }

    @Override
    public ItemEditor modifyCompassMeta(Consumer<CompassEditor> compassEditorConsumer) {
        if (getDefaultItemMeta() instanceof CompassMeta) {
            compassEditorConsumer.accept(new ItemCompassEditor(this));
        }
        return this;
    }

    @Override
    public ItemEditor modifyCrossbowMeta(Consumer<CrossbowEditor> crossbowEditorConsumer) {
        if (getDefaultItemMeta() instanceof CrossbowMeta) {
            crossbowEditorConsumer.accept(new ItemCrossbowEditor(this));
        }
        return this;
    }

    @Override
    public ItemEditor modifyBookMeta(Consumer<BookEditor> bookEditorConsumer) {
        if (getDefaultItemMeta() instanceof BookMeta) {
            bookEditorConsumer.accept(new ItemBookEditor(this));
        }
        return this;
    }

    @Override
    public ItemEditor modifyBannerMeta(Consumer<BannerEditor> bannerEditorConsumer) {
        if (getDefaultItemMeta() instanceof BannerMeta) {
            bannerEditorConsumer.accept(new ItemBannerEditor(this));
        }
        return this;
    }

    @Override
    public ItemEditor modifyMapMeta(Consumer<MapEditor> mapEditorConsumer) {
        if (getDefaultItemMeta() instanceof MapMeta) {
            ItemMapEditor editor = new ItemMapEditor(this);
            mapEditorConsumer.accept(editor);
            editor.save();
        }
        return this;
    }

    private void error(String error) {
        try {
            throw new MetaDataUnavailableException(error);
        } catch (MetaDataUnavailableException e) {
            e.printStackTrace();
            System.out.println("Unable to create Item Meta for Item [" + itemStack.getType().name() + "], editor functions will not work! " + e.getMessage());
        }
    }

    @Override
    public void save() {
        replacePlaceholders();
        itemStack.setItemMeta(itemMeta);
    }

    private void replacePlaceholders() {
        if (placeholders.isEmpty()) return;
        String itemName = getDisplayName();
        for (String key : placeholders.keySet()) {
            if (itemName.contains(key)) {
                itemName = itemName.replace(key, placeholders.get(key));
            }
        }
        setDisplayName(itemName);

        List<String> lore = getLoreOrDefault();
        for (int i = 0; i < lore.size(); i++) {
            for (String key : placeholders.keySet()) {
                String line = lore.get(i);
                if (line.contains(key)) {
                    lore.set(i, ChatColor.translateAlternateColorCodes('&', line.replace(key, placeholders.get(key))));
                }
            }
        }
        itemMeta.setLore(lore);
    }

    @Override
    public ItemStack build() {

        return getBuilder().build();
    }

    private boolean isMetaDataInvalid(String error) {
        if (canUse) return false;
        try {
            throw new MetaDataUnavailableException(error);
        } catch (MetaDataUnavailableException e) {
            System.out.println("Item [" + itemStack.getType().name() + "] can not have any meta data. " + e.getMessage());
        }
        return true;
    }
    public ItemEditor resetMeta(){
        this.itemMeta = Bukkit.getItemFactory().getItemMeta(itemStack.getType());
        return this;
    }

    @Override
    public ItemBuilder getBuilder() {
        return builder;
    }
}
