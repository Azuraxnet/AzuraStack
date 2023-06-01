package com.azura.ui.actions;

import com.azura.ui.gui.UserInterface;
import com.azura.ui.icon.Icon;
import com.azura.ui.screen.Screen;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.InventoryView;

public interface Action {
    Player getPlayer();
    Event getEvent();
    Integer getSlot();
    Screen getScreen();
    Icon getIcon();
    InventoryView getRawInventory();
    UserInterface getUserInterface();
}
