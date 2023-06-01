package com.azura.ui.handler;

import com.azura.ui.actions.EventAction;
import com.azura.ui.gui.UIRegistry;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collections;

public class UIHandler implements Listener {
    private UIRegistry registrar;
    public UIHandler(UIRegistry registrar){
        this.registrar = registrar;
    }
    @EventHandler
    public void onClickEvent(InventoryClickEvent event){
        HumanEntity entity = event.getWhoClicked();
        if(entity instanceof Player) {
            registrar.onEvent(Collections.singletonList(event.getRawSlot()), EventAction.newInstance().setEvent(event).setPlayer((Player) entity));
        }
    }
    @EventHandler
    public void onDragEvent(InventoryDragEvent event){
        HumanEntity entity = event.getWhoClicked();
        if(entity instanceof Player) {
            //How to handle dragging?
            registrar.onEvent(event.getInventorySlots(), EventAction.newInstance().setEvent(event).setPlayer((Player) entity));
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        registrar.kick(event.getPlayer());
    }

    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent event){
        if(event.getPlayer() instanceof Player) {
            registrar.kick((Player)event.getPlayer());
        }
    }
}