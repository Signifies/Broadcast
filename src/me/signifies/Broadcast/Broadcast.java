package me.signifies.Broadcast;

import Listener.Events;
import Utilities.Menus;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Signifies on 4/3/15.
 */
public class Broadcast extends JavaPlugin
{


    /**
     *
     * @author ES (Signifies) of Plugin, Broadcast.
     * All the code in this plugin, and related is the property
     * of of it's creator. You may decompile this resource at will, and if you use a specific section of code unique
     * to this plugin, please make sure I ES, get credited.
     * Developers that contributed to this resource, will be mentioned under authors in the plugin.yml file.
     *
     */

    private Menus menus = new Menus("&a--------- ", "&7>"," &b- ","&f||");

    public PluginDescriptionFile pdfFile = getDescription();
    private ArrayList<String> notify = new ArrayList<>();
    private ArrayList<UUID> author = new ArrayList<>();
    static public boolean DEBUG = false;
    public void onEnable()
    {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new Events(this), this);
        getCommand("sb").setExecutor(new ModBroadcastCommand(this));
        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getCommand("shout").setExecutor(new ShoutCommand(this));
        getCommand("report").setExecutor(new ReportCommand(this));
        getConfig().options().copyDefaults(true);
        saveConfig();
    }



    public ArrayList<String> getNotifications()
    {
        return notify;
    }
    public ArrayList<UUID> getAuthor()
    {
        return author;
    }

    public Menus getMenus()
    {
        return menus;
    }

}
