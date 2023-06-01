package com.azura.ui.screen;

import com.azura.ui.actions.ActionHandler;
import com.azura.ui.actions.DenyActions;
import com.azura.ui.icon.DefaultIcon;
import com.azura.ui.icon.Icon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;


public class DefaultScreen extends AbstractScreen{

    private HashMap<Integer, Icon> iconMap;
    private HashMap<Integer, ActionHandler> handlerHashMap;
    private ActionHandler globalHandler = new DenyActions();
    public DefaultScreen(){
        this.iconMap = new HashMap<>();
        this.handlerHashMap = new HashMap<>();
    }
    public DefaultScreen(int size){
        super(size);
        this.iconMap = new HashMap<>();
        this.handlerHashMap = new HashMap<>();
    }
    @Override
    public Inventory getFrame() {
        Inventory inv = Bukkit.createInventory(null, getSize(), ChatColor.translateAlternateColorCodes('&', getDisplayName()));
        inv.setContents(convertToItemStacks());
        return inv;
    }

    protected ItemStack[] convertToItemStacks(){
        ItemStack[] contents = new ItemStack[getSize()];
        for(int i = 0; i < contents.length; i++){
            Icon icon = iconMap.getOrDefault(i, new DefaultIcon(new ItemStack(Material.AIR)));
            contents[i] = icon.getRepresentation();
        }
        return contents;
    }

    public Screen clearIcon(int slot){
        if(iconMap.containsKey(slot)){
            iconMap.remove(slot);
        }
        return this;
    }
    public Screen clearIcon(int x, int y){
        int slot = convertToSlot(x,y);
        return clearIcon(slot);
    }

    @Override
    public Screen addIcon(int slot, Icon icon) {
        if(isValidPosition(slot)){
            iconMap.put(slot, icon);
            handlerHashMap.put(slot,icon.getActionHandler());
        }
        return this;
    }

    @Override
    public Screen addIcon(int x, int y, Icon icon) {
        int slot = convertToSlot(x,y);
        return addIcon(slot,icon);
    }

    //We should get whatever is in the slot. Have a default icon
    //Set the map if it is null
    @Override
    public Icon getIcon(int slot) {
        if(isValidPosition(slot)){
            return iconMap.get(slot);
        }
        return null;
    }

    @Override
    public Icon getIcon(int x, int y) {
        int slot = convertToSlot(x,y);
        return getIcon(slot);
    }

    @Override
    public void clear() {
        iconMap.clear();
        handlerHashMap.clear();
    }


    @Override
    public void setSlotActionHandler(int slot, ActionHandler actionHandler) {
        handlerHashMap.put(slot,actionHandler);
    }

    @Override
    public ActionHandler getActions(int slot) {
        ActionHandler handler  = super.getActions(slot);
        return handlerHashMap.getOrDefault(slot, handler);
    }
}
