package com.azura.ui.gui;

public abstract class DefaultUserInterface implements UI{
    private UserInterface userInterface;
    public DefaultUserInterface(){

    }
    void setL2UI(UserInterface userInterface){
        this.userInterface = userInterface;
    }
    protected UserInterface getL2UI(){
        return userInterface;
    }

}
