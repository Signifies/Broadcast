package me.es359.Broadcast;

import Listener.Events;
import Utilities.Debug;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ES359 on 4/3/15.
 */
public class Broadcast extends JavaPlugin
{

    public PluginDescriptionFile pdfFile = getDescription();

    static public boolean DEBUG;
    public void onEnable()
    {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new Events(this), this);
        this.getCommand("sb").setExecutor(new ModBroadcastCommand(this));
        this.getCommand("Broadcast").setExecutor(new BroadcastCommand(this));
        this.getCommand("shout").setExecutor(new ShoutCommand(this));
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
//        Debug.debugEnabled();
    }

}
