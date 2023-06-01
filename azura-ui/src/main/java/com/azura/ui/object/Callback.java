package com.azura.ui.object;

import com.azura.ui.gui.DefaultUserInterface;

import java.util.function.Consumer;

public interface Callback<T> {
    DefaultUserInterface setCallback(Consumer<T> consumer);
}
