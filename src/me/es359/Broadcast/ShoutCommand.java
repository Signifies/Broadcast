package me.es359.Broadcast;

import Utilities.BroadcastUtils;
import Utilities.Debug;
import Utilities.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by ES359 on 1/5/16.
 */
public class ShoutCommand extends BroadcastUtils implements CommandExecutor
{

    private Broadcast main;

    public ShoutCommand(Broadcast instance)
    {
        main = instance;
    }

    public HashMap<String, Long> cooldowns = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(getPrefix() + color("&4Sorry, console isn't supported for this yet.\n&aPlease use /alert <msg>"));
            return true;
        }

        Player p = (Player)sender;


        int cooldownTime = this.main.getConfig().getInt("shout-settings.delay");

        String cooldown_msg = this.main.getConfig().getString("shout-settings.delay-msg");
        cooldown_msg = cooldown_msg.replace("%prefix%",getPrefix());
        cooldown_msg = cooldown_msg.replace("%username%", p.getName());

        if (cooldowns.containsKey(p.getName())) {
            long secondsLeft = ((cooldowns.get(p.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
            if (secondsLeft > 0) {
                Debug.log(p, "&7Message should print.");
                cooldown_msg = cooldown_msg.replace("%time_left%","" + secondsLeft);
                p.sendMessage(color(cooldown_msg));
//                Debug.log(Debug.LOG + Debug.ACTION + secondsLeft );
            } else if (secondsLeft <= 0) {
                cooldowns.remove(p.getName());
            }
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("shout"))
        {
            if(!p.hasPermission(Permissions.BROADCAST_SHOUT_PERM))
            {
                p.sendMessage(color(this.main.getConfig().getString("shout-settings.permission-msg")));
            }else {
                if(args.length <1)
                {
                    p.sendMessage(getPrefix() + color("&6Use /shout <message> to broadcast to the entire server. [&bDelay]" ));
                }else if(args.length > 0)
                {


                    StringBuilder str = new StringBuilder();

                    for (int j = 0; j < args.length; j++) {
                        str.append(args[j] + " ");
                    }
                    String shout = str.toString();
                    shout = shout.replace("&", "§");
                    String custom_prefix = color(this.main.getConfig().getString("shout-settings.prefix"));
                    String value = color(this.main.getConfig().getString("shout-settings.format"));
                    Location location;
//                    location = p.getName() +"'s Location: " + ChatColor.GRAY + " " + p.getLocation().getBlockX() +", " + ChatColor.BLUE +p.getLocation().getBlockY() + "" + ChatColor.GREEN + ", " + p.getLocation().getBlockZ());
                    value = value.replace("%message%",shout);
                    value = value.replace("%username%",p.getName());
                    value = value.replace("%world%",p.getWorld().getName());
                    value = value.replace("%custom_prefix%", custom_prefix);
                    value = value.replace("%plugin_prefix%",getPrefix());

                   if(!p.hasPermission("broadcast.cooldown.bypass"))
                   {
                       cooldowns.put(p.getName(), System.currentTimeMillis());

                       Bukkit.getServer().broadcastMessage(value);

                   }else
                   {
                       Bukkit.getServer().broadcastMessage(value);
                   }
                }
            }
        }
        return true;
    }
}
