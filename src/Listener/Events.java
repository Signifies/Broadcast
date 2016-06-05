package Listener;

import Utilities.BroadcastUtils;
import me.es359.Broadcast.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * Created by ES359 on 3/1/16.
 */
public class Events extends BroadcastUtils implements Listener
{

    private Broadcast main;

    public Events(Broadcast instance)
    {
        this.main = instance;
    }

    private boolean joinEnabled()
    {
        return this.main.getConfig().getBoolean("Events.Join.Enabled");
    }
    private boolean quitEnabled()
    {
        return this.main.getConfig().getBoolean("Events.Quit.Enabled");
    }

    public String hasJoinPermission()
    {
        return ""+this.main.getConfig().get("Events.Join.Permission");
    }

    public String hasQuitPermission()
    {
        return ""+this.main.getConfig().get("Events.Quit.Permission");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();
//TODO REMOVED.        authorMessage(main,p);

        authorToggle(main,p);

        //Location location = p.getName() +"'s Location: " +"" + ChatColor.GRAY + " " + p.getLocation().getBlockX() +", " + ChatColor.BLUE +p.getLocation().getBlockY() + " " + ChatColor.GREEN + ", " + p.getLocation().getBlockZ();
        String staffMsg = this.main.getConfig().getString("Events.Join.Staff-Prefix");
        staffMsg = staffMsg.replace("%player%",p.getName());
        staffMsg = staffMsg.replace("%world%",p.getWorld().getName());

        String msg = this.main.getConfig().getString("Events.Join.Message");
        msg = msg.replace("%player%",p.getName());
        msg = msg.replace("%world%",p.getWorld().getName());


        if(joinEnabled())
        {
            if(p.hasPermission(hasJoinPermission()))
            {

                Bukkit.getServer().broadcastMessage(color(staffMsg));
            }else
            {
                broadcastSound(main.getConfig().getString("Message-sounds.sound-on-Join"),main.getConfig().getBoolean("Events.Join.sound-enabled"));
                Bukkit.getServer().broadcastMessage(color(msg));
            }


        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event)
    {
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();



        if(quitEnabled())
        {
            if(p.hasPermission(hasQuitPermission()))
            {
                String staffMsg = this.main.getConfig().getString("Events.Quit.Staff-Prefix");
                staffMsg = staffMsg.replace("%player%",p.getName());
                staffMsg = staffMsg.replace("%world%",p.getWorld().getName());
                Bukkit.getServer().broadcastMessage(color(staffMsg));
            }else
            {
                broadcastSound(main.getConfig().getString("Message-sounds.sound-on-Quit"),main.getConfig().getBoolean("Events.Quit.sound-enabled"));
                String msg = this.main.getConfig().getString("Events.Quit.Message");
                msg = msg.replace("%player%",p.getName());
                msg = msg.replace("%world%",p.getWorld().getName());
                Bukkit.getServer().broadcastMessage(color(msg));
            }
        }
    }
}
