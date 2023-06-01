package com.azura.ui.screen;

import com.azura.ui.actions.ActionHandler;
import com.azura.ui.icon.Icon;
import org.bukkit.inventory.Inventory;

public interface Screen {
    Inventory getFrame();
    Screen setSize(int size);
    Screen setName(String name);
    Screen addIcon(int slot,Icon icon);
    Screen addIcon(int x, int y, Icon icon);
    Icon getIcon(int slot);
    Icon getIcon(int x, int y);
    int getSize();
    void clear();
    void setGlobalActionHandler(ActionHandler actionHandler);
    void setSlotActionHandler(int slot, ActionHandler actionHandler);
    ActionHandler getActions(int slot);
}
