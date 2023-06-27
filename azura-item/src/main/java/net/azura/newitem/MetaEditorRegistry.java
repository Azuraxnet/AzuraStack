package net.azura.newitem;

import net.azura.newitem.general.GenericMetaEditor;
import net.azura.newitem.general.ItemRepresentation;
import net.azura.newitem.general.PersistantContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Consumer;

public class MetaEditorRegistry<M extends ItemMeta> implements GenericMetaEditor<M> {
    private final M meta;
    private final Class<M> supportedClass;
    private final PersistantContainer container;
    private final ItemRepresentation representation;
    private MetaEditorRegistry(M meta,Class<M> supportedClass, PersistantContainer container, ItemRepresentation representation){
        this.meta = meta;
        this.container = container;
        this.representation = representation;
        this.supportedClass = supportedClass;
    }

    @Override
    public ItemStack build() {
        return new ItemStack(Material.STICK);
    }

    @Override
    public Class<M> getMetaClass() { //watdf
        return supportedClass;
    }

    public M getMeta(){
       ItemMeta cloned = meta.clone();
       if(cloned.getClass().isAssignableFrom(meta.getClass())){
           return (M) cloned;
       }
       return meta;
    }

    public boolean isCompatibleWith(Class<? extends ItemMeta> clazz){
        return getMeta().getClass().isAssignableFrom(clazz);
    }


    public  <T extends GenericMetaEditor<M>> void editMeta(Class<T> clazz, Consumer<Void> consumer, Runnable error){
        // Instantiate T from Class<T>
        T t = null;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            error.run();
            return;
        }

        // Check if Class<T> can support the meta that we would like. MetaEditor would have something like MetaEditor<BookMeta>
        if(!t.isCompatibleWith(getMetaClass())) {
            error.run();
            return;
        }

        // If T supports the MetaEditor type and instantiation was successful, call the consumer.
        consumer.accept(null);
    }

    public <T extends GenericMetaEditor<M>> void editMeta(Class<T> clazz, Consumer<Void> consumer){
        editMeta(clazz, consumer, () -> {
            // Handle error here. This could involve logging the error, or some other kind of notification.
            System.err.println("Error occurred in editMeta. The specified MetaEditor does not support the desired meta type.");
        });
    }

    @Override
    public void addLoreLine(String lore) {
         representation.addLoreLine(lore);
    }

    @Override
    public void setLore(String... lore) {
        representation.setLore(lore);
    }

    @Override
    public void setLore(List<String> lore) {
        representation.setLore(lore);
    }

    @Override
    public void clearLore() {
        representation.clearLore();
    }

    @Override
    public void setLore(int line, String lore) {
        representation.setLore(line, lore);
    }

    @Override
    public String getLore(int line) {
        return representation.getLore(line);
    }

    @Override
    public List<String> getLore() {
        return representation.getLore();
    }

    @Override
    public int getLoreSize() {
        return representation.getLoreSize();
    }

    @Override
    public void setDisplayName(String displayName) {
        representation.setDisplayName(displayName);
    }

    @Override
    public String getDisplayName() {
        return representation.getDisplayName();
    }

    @Override
    public void setTag(@NotNull Plugin plugin, @NotNull String key, @NotNull String value) {
        container.setTag(plugin,key,value);
    }

    @Override
    public void clearTags(@NotNull Plugin plugin, @NotNull String key) {
        container.clearTags(plugin, key);
    }

    @Override
    public String getTag(@NotNull Plugin plugin, @NotNull String key) {
        return container.getTag(plugin, key);
    }
}
