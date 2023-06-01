package com.azura.ui.gui;

import com.azura.ui.screen.Screen;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface UI {
    Screen getScreen(Player player);
    boolean onJoin(Player player);
    void onClose();
    void onLeave(OfflinePlayer player);
    void onInitialize();
}
