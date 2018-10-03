package me.signifies.Broadcast;

import Utilities.BroadcastUtils;
import Utilities.Debug;
import Utilities.Permissions;
import org.bukkit.Bukkit;
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


    private Broadcast instance;

    public ShoutCommand(Broadcast instance)
    {
        instance = instance;
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

        int cooldownTime = instance.getConfig().getInt("shout-settings.delay");

        String cooldown_msg = instance.getConfig().getString("shout-settings.delay-msg");
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
                p.sendMessage(color(instance.getConfig().getString("shout-settings.permission-msg")));
            }else {
                if(args.length <1)
                {
                    sendText(instance.getMenus().commandShout(),p);
                }else if(args.length > 0)
                {

                    StringBuilder str = new StringBuilder();

                    for (int j = 0; j < args.length; j++) {
                        str.append(args[j] + " ");
                    }

                    String shout = str.toString();

                    if(shout.contains("-help"))
                    {
                        sendText(instance.getMenus().commandShout(),p);
                        return true;
                    }

                    if(shout.contains("-functions"))
                    {
                        sendText(instance.getMenus().shoutFunctions(),p);
                        return true;
                    }

                    if(p.hasPermission(Permissions.BROADCAST_SHOUT_FORMAT_COLOR))
                    {
                        shout = shout.replace("&", "§");
                    }

                    if(p.hasPermission(Permissions.BROADCAST_SHOUT_FORMAT_NAME))
                    {
                        shout = shout.replace("#name",p.getName());
                    }

                    if(p.hasPermission(Permissions.BROADCAST_SHOUT_FORMAT_UUID))
                    {
                        shout = shout.replace("#uuid",p.getUniqueId().toString());
                    }

                    if(p.hasPermission(Permissions.BROADCAST_SHOUT_FORMAT_WORLD))
                    {
                        shout = shout.replace("#world",p.getWorld().getName());

                    }

                    if(p.hasPermission(Permissions.BROADCAST_SHOUT_FORMAT_EXP))
                    {
                        shout = shout.replace("#exp",""+p.getExpToLevel());
                    }

                  String location =  color("&7X:&a"+p.getLocation().getBlockX() +" &7Y&a:" +p.getLocation().getBlockY() + " &7Z&a:" + p.getLocation().getBlockZ() +"&r" );
                    shout = shout.replace("#location",location);
                  if(p.hasPermission(Permissions.BROADCAST_SHOUT_FORMAT_LOCATION))
                  {
                      shout = shout.replace("#location",location);
                  }

//                    canFormat(shout,p);

                    String custom_prefix = color(instance.getConfig().getString("shout-settings.prefix"));
                    String value = color(instance.getConfig().getString("shout-settings.format"));


//
                    value = value.replace("%message%",shout);
                    value = value.replace("%username%",p.getName());
                    value = value.replace("%world%",p.getWorld().getName());
                    value = value.replace("%custom_prefix%", custom_prefix);
                    value = value.replace("%plugin_prefix%",getPrefix());


                   if(!p.hasPermission("broadcast.cooldown.bypass"))
                   {
                       cooldowns.put(p.getName(), System.currentTimeMillis());

                       Bukkit.getServer().broadcastMessage(value);
                       broadcastSound(instance.getConfig().getString("Message-sounds.shoutbroadcast-sound"),instance.getConfig().getBoolean("shout-settings.Sound-on-shout"));
                   }else
                   {
                       Bukkit.getServer().broadcastMessage(value);
                       broadcastSound(instance.getConfig().getString("Message-sounds.shoutbroadcast-sound"),instance.getConfig().getBoolean("shout-settings.Sound-on-shout"));
                   }
                }
            }
        }
        return true;
    }

}
