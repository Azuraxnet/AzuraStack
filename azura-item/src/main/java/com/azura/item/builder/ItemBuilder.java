package com.azura.item.builder;

import com.azura.item.editors.ItemEditor;
import com.azura.item.exception.InvalidStackSizeException;
import com.azura.item.exception.MaterialParseException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ItemBuilder {
    private final ItemStack itemStack;
    private final ItemEditor adapter;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        adapter = new ItemMetaEditor(this, itemStack);
    }

    public ItemBuilder(String materialString) {
        Material m = Material.matchMaterial(materialString);
        if (m == null) {
            throw new MaterialParseException("Unable to match the material from the name: " + materialString);
        }
        itemStack = new ItemStack(m);
        adapter = new ItemMetaEditor(this, itemStack);
    }

    ItemBuilder(ItemEditor editor, ItemStack itemStack) {
        this.itemStack = itemStack;
        this.adapter = editor;
    }

    public ItemBuilder setAmount(int amount) {
        if (amount > itemStack.getMaxStackSize() && itemStack.getMaxStackSize() != -1) {
            try {
                throw new InvalidStackSizeException("Cannot set the amount to " + amount + ". The material [" + itemStack.getType().name() + "] maximum stack size is " + itemStack.getMaxStackSize() + ". Defaulting to " + itemStack.getMaxStackSize() + ".");
            } catch (InvalidStackSizeException e) {
                System.out.println(e.getMessage());
            }
            itemStack.setAmount(itemStack.getMaxStackSize());
            return this;
        }
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setAmountToMaximum() {
        if (itemStack.getMaxStackSize() == -1) {
            itemStack.setAmount(64);
        }
        itemStack.setAmount(itemStack.getMaxStackSize());
        return this;
    }

    public ItemEditor getEditor() {
        if (adapter.canEdit()) {
            return adapter;
        }
        return new BlankItemEditor(this);
    }

    public boolean canEdit() {
        return adapter.canEdit();
    }

    public ItemStack build() {
        if (adapter.canEdit()) {
            adapter.save();
        }
        return itemStack.clone();
    }


}
