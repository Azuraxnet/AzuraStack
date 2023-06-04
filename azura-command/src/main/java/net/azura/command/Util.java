package net.azura.command;

import org.bukkit.ChatColor;

public class Util {
    public static String colorize(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
