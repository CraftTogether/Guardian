package com.github.crafttogether.hydroperms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Plugin extends JavaPlugin {

    private static JavaPlugin plugin;

    public static JavaPlugin getInstance() {
        return plugin;
    }

    public static String getFilePath() {
        return getInstance().getDataFolder().getParentFile() + "/permissions.json";
    }

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        try {
            getConfig().load(Files.newBufferedReader(Path.of(getFilePath())));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "HydroPerms loaded");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "HydroPerms disabled");
    }
}
