package com.azura.ui.actions;

import org.bukkit.event.Cancellable;

public class DenyActions implements ActionHandler {

    @Override
    public void runChecks(Action action) {
        if(action.getEvent() instanceof Cancellable){
            if(!((Cancellable) action.getEvent()).isCancelled())
                ((Cancellable) action.getEvent()).setCancelled(true);
        }
    }
}
