package me.hsgamer.nbtcommanditems;

import me.hsgamer.nbtcommanditems.commands.PluginCommand;
import me.hsgamer.nbtcommanditems.listeners.Listeners;
import org.bstats.bukkit.Metrics;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class NBTCommandItems extends JavaPlugin {
    private static NBTCommandItems instance;
    private static Variable variable;
    private ConfigFile configFile;
    private boolean legacy = false;

    public static NBTCommandItems getInstance() {
        return instance;
    }

    public static Variable getVariable() {
        return variable;
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }

    @Override
    public void onEnable() {
        instance = this;
        legacy = getServer().getVersion().contains("1.8") || getServer().getVersion().contains("1.7");
        configFile = new ConfigFile(this);
        variable = new Variable();
        new Metrics(this);
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        PluginCommand command = new PluginCommand();
        getCommand("commanditems").setExecutor(command);
        getCommand("commanditems").setTabCompleter(command);
    }

    @Override
    public void onDisable() {
        configFile = null;
        instance = null;
        variable = null;
        HandlerList.unregisterAll(this);
    }

    public boolean isLegacy() {
        return legacy;
    }
}
