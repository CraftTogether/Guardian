package com.github.crafttogether.guardian;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private static JavaPlugin plugin;

    public static JavaPlugin getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Guardian loaded");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Guardian disabled");
    }
}
