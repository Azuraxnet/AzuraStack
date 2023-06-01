package com.azura.ui.gui;

import com.azura.ui.actions.Action;
import com.azura.ui.actions.ActionHandler;
import com.azura.ui.actions.EventAction;
import com.azura.ui.icon.Icon;
import com.azura.ui.screen.Screen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractUI implements UserInterface {
    private HashMap<UUID, Screen> users = new HashMap<>();
    private DefaultUserInterface defaultUserInterface;
    private UIRegistry registrar;
    private boolean inUse;
    private boolean cancelByDefault = false;
    public AbstractUI(UIRegistry registrar, DefaultUserInterface defaultUserInterface){
        this.registrar = registrar;
        this.defaultUserInterface = defaultUserInterface;
        this.inUse = false;
        defaultUserInterface.setL2UI(this);
    }

    @Override
    public boolean join(Player player) {
        if(!player.isOnline()) return false;
        player.closeInventory();
        if(!isInUse()){
            registrar.registerInterface(this);
            inUse = true;
        }
        if(defaultUserInterface.onJoin(player)){
            player.openInventory(getAndUpdateInventory(player));
            return true;
        }else{
            player.sendMessage(ChatColor.RED+"You cannot access this menu!");
        }
        return false;
    }

    @Override
    public boolean kick(OfflinePlayer offlinePlayer) {
        if(users.containsKey(offlinePlayer.getUniqueId())) {
            defaultUserInterface.onLeave(offlinePlayer);
            users.remove(offlinePlayer.getUniqueId());
            if(offlinePlayer.isOnline()){
                offlinePlayer.getPlayer().closeInventory();
            }
            if(users.isEmpty()){
                defaultUserInterface.onClose();
                registrar.unregisterInterface(this);
                this.inUse = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isInUse() {
        return inUse;
    }

    @Override
    public boolean inInterface(Player player) {
        return users.containsKey(player.getUniqueId());
    }

    @Override
    public void close() {
        Collection<OfflinePlayer> players = users.keySet().stream().map((uuid) -> Bukkit.getOfflinePlayer(uuid)).collect(Collectors.toList());
        players.forEach((this::kick));
    }

    private void updateMap(){
        users.keySet().stream().map(Bukkit::getOfflinePlayer).filter((offlinePlayer -> !offlinePlayer.isOnline())).forEach(this::kick);
        Iterator<Map.Entry<UUID, Screen>> uuidIterator = users.entrySet().iterator();
        while(uuidIterator.hasNext()) {
            Map.Entry<UUID,Screen> entry = uuidIterator.next();
            Player p = Bukkit.getPlayer(entry.getKey());
            if(p == null) return;
            getAndUpdateInventory(p);
        }
    }

    @Override
    public void simpleRefresh() {
        updateMap();
        for(Map.Entry<UUID, Screen> entry : users.entrySet()){
            Player p = Bukkit.getPlayer(entry.getKey());
            if(p == null) return;
            p.getOpenInventory().getTopInventory().setContents(getScreenFrame(users.get(p.getUniqueId()), p).getContents());
            p.updateInventory();
        }
    }


    @Override
    public void fullRefresh() {
        updateMap();
        for(Map.Entry<UUID, Screen> entry : users.entrySet()){
            Player p = Bukkit.getPlayer(entry.getKey());
            if(p == null) return;
            Screen screen = users.get(p.getUniqueId());
            users.remove(p.getUniqueId());
            Inventory inventory = getScreenFrame(screen, p);
            p.closeInventory();
            users.put(p.getUniqueId(), screen);
            p.openInventory(inventory);
        }
    }

    private Inventory getScreenFrame(Screen screen, Player player){
        if(defaultUserInterface instanceof PersistentInterface){
            Inventory inv = screen.getFrame();
            ItemStack[] screenContents = inv.getContents();
            ItemStack[] existingContents = player.getOpenInventory().getTopInventory().getContents();
            for(int i = 0; i < screenContents.length; i++){
                if(existingContents.length <= i) break;
                Icon icon = screen.getIcon(i);
                //TODO: Differentiate from an updated to new
                if(icon == null && existingContents[i] != null){
                    screenContents[i] = existingContents[i];
                }
            }
            inv.setContents(screenContents);
            return inv;
        }
        return screen.getFrame();
    }


    private Inventory getAndUpdateInventory(Player player){
        Screen s = defaultUserInterface.getScreen(player);
        if(users.containsKey(player.getUniqueId())) {
            users.replace(player.getUniqueId(), s);
        }else{
            users.put(player.getUniqueId(), s);
        }
        return getScreenFrame(users.get(player.getUniqueId()),player);
    }

    void runActions(Collection<Integer> position, EventAction.ActionBuilder builder){
        Screen screen = users.get(builder.getPlayer().getUniqueId());
        builder.setScreen(screen);
        for(Integer i : position){
            builder.setSlot(i);
            ActionHandler handers =screen.getActions(i);
            if(cancelByDefault){
                Action a =builder.build();
                if(a.getEvent() instanceof Cancellable){
                    ((Cancellable) a.getEvent()).setCancelled(true);
                }
                handers.runChecks(builder.build());
            }else {
                handers.runChecks(builder.build());
            }
        }
    }
    @Override
    public void setCanceledByDefault(boolean b) {
        this.cancelByDefault = b;
    }

    @Override
    public boolean isCanceledByDefault() {
        return cancelByDefault;
    }

}