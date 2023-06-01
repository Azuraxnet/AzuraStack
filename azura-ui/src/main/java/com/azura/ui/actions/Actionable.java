package com.azura.ui.actions;

public interface Actionable {
    ActionHandler getActionHandler();
    Actionable setActionHandler(ActionHandler handler);
}
