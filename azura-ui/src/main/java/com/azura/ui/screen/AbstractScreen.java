package com.azura.ui.screen;

import com.azura.ui.actions.ActionHandler;
import com.azura.ui.actions.DenyActions;

public abstract class AbstractScreen implements Screen{
    private String displayName;
    private int size;
    private ActionHandler globalActionHandler = new DenyActions();
    private boolean cancelByDefault = true;
    public AbstractScreen(){
        this.size = 9;
        this.displayName = "User Interface";
    }
    public AbstractScreen(int size){
        this.size = size;
        this.displayName = "User Interface";
    }

    @Override
    public Screen setSize(int size) {
        if(size > 0 && size % 9 == 0){
            this.size = size;
        }
        return this;
    }

    @Override
    public Screen setName(String name) {
        this.displayName = name;
        return this;
    }

    @Override
    public void setGlobalActionHandler(ActionHandler actionHandler) {
        this.globalActionHandler = actionHandler;
    }

    @Override
    public ActionHandler getActions(int slot) {
        return globalActionHandler;
    }
    protected boolean isValidPosition(int slot){
        return slot >= 0 && slot < size;
    }
    protected int convertToSlot(int x, int y){
        return (y*9) +x;
    }
    public int getSize(){
        return size;
    }
    protected String getDisplayName(){
        return displayName;
    }


}
