package com.azura.ui.screen;

import com.azura.ui.icon.DefaultIcon;
import com.azura.ui.icon.Icon;
import com.azura.ui.object.PaginatedList;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaginatedScreen extends DefaultScreen{
    /**
     * Okay so, the indexes are handled here.
     * The screen will conform to the item indexes
     * The size of the screen must be  at least ( itemIndexes length +2 )
     * The paginations must be implemented here I believe -- They can't be. We would need to refresh screen.
     */
    private PaginatedList paginatedList;
    private int[] itemIndexes = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
    private int previousSlot = 0;
    private int nextSlot = 35;
    private Icon previousIcon;
    private Icon nextIcon;

    public PaginatedScreen(Icon previousIcon, Icon nextIcon){
        super(36);
        this.previousIcon = previousIcon;
        this.nextIcon = nextIcon;
        this.paginatedList = new PaginatedList(itemIndexes.length);
    }

    @Override
    public Inventory getFrame() {
        Inventory inv = Bukkit.createInventory(null, getSize(), ChatColor.translateAlternateColorCodes('&', getDisplayName() + "&7[" + paginatedList.getPageIndex() + "/" + paginatedList.getMaxPages() + "]"));
        updateMap();
        inv.setContents(convertToItemStacks());
        return inv;
    }
    protected void updateMap(){
        List<Icon> list = paginatedList.getPage();
        for(int i = 0; i < itemIndexes.length; i++){
            if(i >= list.size()){
                //TODO: IF icon already exists use the one that is there
                addIcon(itemIndexes[i], new DefaultIcon(new ItemStack(Material.AIR)));
                continue;
            }
            addIcon(itemIndexes[i], list.get(i));
        }
        addIcon(previousSlot, previousIcon);
        addIcon(nextSlot, nextIcon);

    }

    public void clearPages(){
        this.paginatedList.clear();
    }
    public int getCurrentPage(){
        return this.paginatedList.getPageIndex()-1;
    }
    public Screen addToList(Icon icon){
        paginatedList.add(icon);
        return this;
    }
    public Screen addAll(List<Icon> iconList){
        paginatedList.addAll(iconList);
        return this;
    }

    public int[] getItemIndexes() {
        return Arrays.copyOf(itemIndexes, itemIndexes.length);
    }

    public void nextPage(){
        paginatedList.nextPage();
    }
    public void previousPage(){
        paginatedList.previousPage();
    }
    public void setNextIcon(Icon nextIcon) {
        this.nextIcon = nextIcon;
    }

    public void setPreviousIcon(Icon previousIcon) {
        this.previousIcon = previousIcon;
    }

    public void setNextSlot(int nextSlot) {
        if(nextSlot > 0 && nextSlot < getSize()-1 && isValidSlot(nextSlot)) {
            this.nextSlot = nextSlot;
        }
    }

    public void setPreviousSlot(int previousSlot) {
        if(previousSlot > 0 && previousSlot < getSize()-1 && isValidSlot(previousSlot)) {
            this.previousSlot = previousSlot;
        }
    }
    private boolean isValidSlot(int slot){
        for(int i = 0; i < itemIndexes.length; i++){
            if(slot == itemIndexes[i]){
                return false;
            }
            if(itemIndexes[i] > slot){
                return true;
            }
        }
        return true;
    }


    private void reconfigure(){
        if(getSize() < getMinimalSize()){
            setSize(getMinimalSize());
        }
        List<Integer> availableSlots = new ArrayList<>();
        Integer[] outputBoxed = ArrayUtils.toObject(itemIndexes);
        List<Integer> usedSlots = new ArrayList<>(Arrays.asList(outputBoxed));
        for(int i = 0; i < getSize(); i++){
            if(!usedSlots.contains(i)){
                availableSlots.add(i);
            }
        }
        availableSlots.sort(Integer::compare);
        if(availableSlots.contains(usedSlots.get(0)-1)){
            previousSlot = usedSlots.get(0)-1;
        }else{
            previousSlot = availableSlots.get(0);
        }
        if(availableSlots.contains(usedSlots.get(usedSlots.size()-1)+1)){
            nextSlot = usedSlots.get(usedSlots.size()-1)+1;
        }else{
            nextSlot= availableSlots.get(1);
        }
    }
    public void updateIndexes(int[] itemIndexes) {
        Arrays.sort(itemIndexes);
        if(itemIndexes[0] < 0){
            return;
        }
        this.itemIndexes = itemIndexes;
        this.paginatedList.updateItemsPerPage(itemIndexes.length);
        reconfigure();
    }

    @Override
    public Screen setSize(int size) {
        if(size <getMinimalSize()){
            super.setSize(getMinimalSize());
        }else {
            super.setSize(size);
        }
        reconfigure();
        return this;
    }
    private int getMinimalSize(){
        int listSize = itemIndexes.length+2;
        int menuSize = (listSize/9) *9;
        if(listSize % 9 > 0){
            menuSize+= 9;
        }
        return menuSize;
    }
}
