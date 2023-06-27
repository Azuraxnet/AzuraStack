package net.azura.newitem.general;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public interface GenericMetaEditor<M extends ItemMeta> extends PersistantContainer, ItemRepresentation {

    /* General */
    ItemStack build();
    Class<M> getMetaClass();
    M getMeta();
    //Lets see if we can make user friendly later
    boolean isCompatibleWith(Class<? extends ItemMeta> clazz);
    <T extends GenericMetaEditor<M>> void editMeta(Class<T> clazz, Consumer<Void> consumer);
    <T extends GenericMetaEditor<M>> void editMeta(Class<T> clazz, Consumer<Void> consumer, Runnable error);

}
