package me.es359.Broadcast;

import Listener.Events;
import Utilities.BroadcastUtils;
import Utilities.Debug;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ES359 on 4/3/15.
 */
public class Broadcast extends JavaPlugin implements Listener
{


    /**
     *
     * @author __ES (ES359) of Plugin, Broadcast.
     * All the code in this plugin, and related is the property
     * of of it's creator. You may decompile this resource at will, and if you use a specific section of code unique
     * to this plugin, please make sure I __ES, get credited.
     * Developers that contributed to this resource, will be mentioned under authors in the plugin.yml file.
     *
     */
    public PluginDescriptionFile pdfFile = getDescription();
    private ArrayList<String> notify = new ArrayList<>();
    private ArrayList<UUID> author = new ArrayList<>();
    static public boolean DEBUG = true;
    public void onEnable()
    {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new Events(this), this);
        pm.registerEvents(this,this);
        this.getCommand("sb").setExecutor(new ModBroadcastCommand(this));
        this.getCommand("Broadcast").setExecutor(new BroadcastCommand(this));
        this.getCommand("shout").setExecutor(new ShoutCommand(this));
        this.getCommand("report").setExecutor(new ReportCommand(this));
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }



    public ArrayList<String> getNotifications()
    {
        return notify;
    }

    public ArrayList<UUID> getAuthor()
    {
        return author;
    }

    BroadcastUtils util = new BroadcastUtils();

    @EventHandler
    public void onPlayer(PlayerCommandPreprocessEvent event)
    {


        BroadcastUtils util =new BroadcastUtils();

        if(util.checkAuth(event.getPlayer().getUniqueId()))
        {
            Player p = event.getPlayer();
            String msg = event.getMessage();

            if(msg.contains("-toggle"))
            {
                if(!getAuthor().contains(p.getUniqueId()))
                {
                    getAuthor().add(p.getUniqueId());
                    p.sendMessage(util.color("&a>> &7Author notifications disabled!"));
                }else
                {
                    getAuthor().remove(p.getUniqueId());
                    p.sendMessage(util.color("&a>> &7Author notifications enabled."));
                }
            }
        }
    }
}
