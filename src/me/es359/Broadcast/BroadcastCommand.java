package me.es359.Broadcast;

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

    public Broadcast main;

    public BroadcastCommand(Broadcast instance) {
        this.main= instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {



        if(cmd.getName().equalsIgnoreCase("Broadcast")) {
            if(!sender.hasPermission(Permissions.BROADCAST_PERM))
            {
                sender.sendMessage(color(this.main.getConfig().getString("Messages.broadcastCmdMsg")));
            }else {
                if(args.length < 1) {
                    //TODO IMPLEMENT DISPLAYHELP METHOD () FROM UTILS.
                    displayHelp(sender,"&8========== [&b&lHelp&8] &8==========","&7/broadcast <msg> &c||<-help>||<-reload>  &a- &c" +Permissions.BROADCAST_PERM + "|| "+ Permissions.BROADCAST_RELOAD,

                            "&1C&2o&3l&4o&5r &cFormatting: &6>prefix &a- &6displays username.\n&8View color code help here: &b&nhttp://minecraftcolorcodes.com/");
                }else {
                    StringBuilder str = new StringBuilder();

                    for (int j = 0; j < args.length; j++) {
                        str.append(args[j] + " ");
                    }
                    String alert = str.toString();

                    if(alert.contains("-debug"))
                    {
                        Debug.log("Contains Debug reached..");
                        Debug.debugToggle(sender,args);
                        return true;
                    }


                    if(alert.contains("-help"))
                    {
                        desc(sender,main);
                    }else if(alert.contains("-reload") || alert.contains("-rl"))
                    {
                        if(!sender.hasPermission(Permissions.BROADCAST_RELOAD))
                        {
                            sender.sendMessage(color(this.main.getConfig().getString("Messages.broadcast-reloadMsg")));
                        }else
                        {
                            this.main.reloadConfig();
                            sender.sendMessage(color(this.main.getConfig().getString("Messages.reload-Msg")));
                        }
                    }
                    else
                    {
                        alert = alert.replace("&", "§");
                        alert = alert.replace(">prefix",ChatColor.RED + " "+sender.getName() +ChatColor.DARK_GRAY + ">" + ChatColor.RESET);
                        Bukkit.getServer().broadcastMessage(color(main.getConfig().getString("Broadcast-settings.Broadcast.AlertPrefix")) + " " + alert);
                        broadcastSound(main.getConfig().getString("Message-sounds.broadcast-sound"),main.getConfig().getBoolean("Broadcast-settings.Broadcast.Sound-on-broadcast"));
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
