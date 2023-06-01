package com.azura.ui.gui;

import com.azura.ui.AzuraUI;
import com.azura.ui.actions.EventAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UIRegistry implements Listener {
    private final List<UserInterface> userIntefaceList;
    private int taskId;
    public UIRegistry(){
        userIntefaceList = Collections.synchronizedList(new ArrayList<>());
        activeInterfaces();
    }

    public UserInterface getStaticL2UserInterface(DefaultUserInterface defaultUserInterface){
        if(defaultUserInterface == null){
            return null;
        }
        UserInterface ui = new StaticUI(this, defaultUserInterface);
        defaultUserInterface.onInitialize();
        return ui;
    }
    public UserInterface getAnimatedL2UserInterface(DefaultUserInterface defaultUserInterface){
        if(defaultUserInterface == null){
            return null;
        }
        UserInterface ui = new AnimatedUI(this, defaultUserInterface);
        defaultUserInterface.onInitialize();
        return ui;
    }
    public void closeAll(){
        for(UserInterface userInteface : userIntefaceList){
            userInteface.close();
        }
    }
    protected void unregisterInterface(UserInterface userInterface){
        if(userIntefaceList.contains(userInterface)){
            this.userIntefaceList.remove(userInterface);
        }
    }
    protected void registerInterface(UserInterface userInterface){
        if(!userIntefaceList.contains(userInterface)){
            this.userIntefaceList.add(userInterface);
        }
    }

    public void onEvent(Collection<Integer> integers, EventAction.ActionBuilder builder){
        UserInterface userInterface = getUserInterface(builder.getPlayer());
        if(userInterface == null) return;
        if(userInterface instanceof AbstractUI){
            builder.setL2UserInterface(userInterface);
            ((AbstractUI) userInterface).runActions(integers, builder);
        }
    }

    public void kick(Player player){
        UserInterface userInterface = getUserInterface(player);
        if(userInterface == null) return;
        userInterface.kick(player);
    }
    public UserInterface getUserInterface(Player player){
        for(UserInterface u : userIntefaceList){
            if(u.inInterface(player)){
                return u;
            }
        }
        return null;
    }

    //Animated
    private void activeInterfaces(){
        this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(AzuraUI.getOwningPlugin(), ()->{
            userIntefaceList.stream().filter((ui)->ui instanceof AnimatedUI).forEach((ui)->{
                if(((AnimatedUI) ui).shouldRefresh()){
                    ui.simpleRefresh();
                }
            });
        }, 20L, 1L);
    }
}
