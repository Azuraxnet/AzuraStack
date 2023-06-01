package com.azura.ui.gui;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface UserInterface {
    boolean join(Player player);
    boolean kick(OfflinePlayer palyer);
    boolean isInUse();
    boolean inInterface(Player player);
    void close();
    void simpleRefresh();
    void fullRefresh();
    boolean isCanceledByDefault();
    void setCanceledByDefault(boolean b);
}
