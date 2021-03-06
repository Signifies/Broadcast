package me.signifies.Broadcast;

import Utilities.BroadcastUtils;
import Utilities.Debug;
import Utilities.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by ES359 on 4/3/15.
 */
public class BroadcastCommand extends BroadcastUtils implements CommandExecutor {

    private Broadcast instance;

    public BroadcastCommand(Broadcast broadcast) {
        instance= broadcast;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
        if(!sender.hasPermission(Permissions.BROADCAST_PERM))
        {
                sender.sendMessage(color(instance.getConfig().getString("Messages.broadcastCmdMsg")));
            }else {
                if(args.length < 1) {
                   sendText(instance.getMenus().commandBroadcast(),sender);
                }else {
                    StringBuilder str = new StringBuilder();

                    for (String arg : args) {
                        str.append(arg + " ");
                    }
                    String alert = str.toString();

                    if(alert.contains("-ver") || alert.contains("-version"))
                    {
                      sender.sendMessage(getPluginVersion(instance,sender));
                        return true;
                    }

                    if(alert.contains("-help"))
                    {
                        sendText(instance.getMenus().commandList(), sender);
                    }else if(alert.contains("-reload") || alert.contains("-rl"))
                    {
                        if(!sender.hasPermission(Permissions.BROADCAST_RELOAD))
                        {
                            sender.sendMessage(color(instance.getConfig().getString("Messages.broadcast-reloadMsg")));
                        }else
                        {
                            instance.reloadConfig();
                            sender.sendMessage(color(instance.getConfig().getString("Messages.reload-Msg")));
                        }
                    }else if(alert.contains("-about"))
                    {
                        desc(sender,instance);
                    }
                    else
                    {
                        //alert = alert.replace("&", "§");
                        alert = alert.replace(">prefix",ChatColor.RED + " "+sender.getName() +ChatColor.DARK_GRAY + ">" + ChatColor.RESET);
                        Bukkit.getServer().broadcastMessage(color(instance.getConfig().getString("Broadcast-settings.Broadcast.AlertPrefix")) + " " + alert);
                        broadcastSound(instance.getConfig().getString("Message-sounds.broadcast-sound"),instance.getConfig().getBoolean("Broadcast-settings.Broadcast.Sound-on-broadcast"));
                        return true;
                    }
                }
            }
        return true;
    }
}
