package com.azura.ui.gui;

import java.security.InvalidParameterException;

public class AnimatedUI extends AbstractUI {
    private int refreshRate = 20;
    private int lastRefresh;
    public AnimatedUI(UIRegistry registrar, DefaultUserInterface defaultUserInterface) {
        super(registrar, defaultUserInterface);
    }
    public void setRefreshRate(int rate){
        if(rate < 0){
            try{
                throw new InvalidParameterException("The value cannot be less than 0");
            }catch (InvalidParameterException e){
                e.printStackTrace();
                return;
            }
        }
        this.refreshRate = rate;
    }

    boolean shouldRefresh(){
        if(refreshRate == 0){
            return false;
        }
        if(++lastRefresh == refreshRate){
            lastRefresh = 0;
            return true;
        }
        return false;
    }

}