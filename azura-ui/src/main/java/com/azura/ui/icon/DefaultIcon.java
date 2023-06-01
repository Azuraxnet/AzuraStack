package com.azura.ui.icon;

import com.azura.ui.actions.ActionHandler;
import com.azura.ui.actions.DenyActions;
import org.bukkit.inventory.ItemStack;

public class DefaultIcon implements Icon {
    public ItemStack representation;
    private ActionHandler actionHandler = new DenyActions();
    public DefaultIcon(final ItemStack itemStack){
        this.representation = itemStack.clone();
    }
    @Override
    public ItemStack getRepresentation() {
        return this.representation.clone();
    }

    @Override
    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    @Override
    public DefaultIcon setActionHandler(ActionHandler handler) {
        this.actionHandler = handler;
        return this;
    }
    public void update(ItemStack itemStack){
        this.representation = itemStack;
    }


}

