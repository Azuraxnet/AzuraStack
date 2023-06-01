package com.azura.ui.actions;

@FunctionalInterface
public interface ActionHandler {
    void runChecks(Action action);
}