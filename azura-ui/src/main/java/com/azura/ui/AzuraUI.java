package com.azura.ui;

import com.azura.ui.gui.UIRegistry;
import com.azura.ui.handler.UIHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class AzuraUI {
    private static AzuraUI instance;
    private final Plugin javaPlugin;
    private final UIRegistry registrar;
    private AzuraUI(Plugin plugin){
        this.javaPlugin = plugin;
        javaPlugin.getLogger().info("Loading L2UI");
        instance = this;
        registrar = new UIRegistry();
        Bukkit.getPluginManager().registerEvents(new UIHandler(registrar), javaPlugin);
        javaPlugin.getLogger().info("L2UI is now loaded");
    }

    public UIRegistry getRegistrar() {
        return registrar;
    }

    private Plugin getJavaPlugin(){
        return javaPlugin;
    }
    public static void initialize(Plugin javaPlugin){
        if(instance == null){
            instance = new AzuraUI(javaPlugin);
        }
    }

    public static AzuraUI getInstance() {
        if(instance != null) {
            return instance;
        }
        return null;
    }

    public static Plugin getOwningPlugin(){
        return instance.getJavaPlugin();
    }
}
