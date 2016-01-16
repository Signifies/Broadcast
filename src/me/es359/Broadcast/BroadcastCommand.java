package me.es359.Broadcast;

import Utilities.BroadcastUtils;
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
                sender.sendMessage(ChatColor.YELLOW +"You need the permission, broadcast.use to complete that.");
            }else {
                if(args.length < 1) {
                    sender.sendMessage(color("&a/broadcast <message> [&1C&2o&3l&4o&5r &ccodes can be used. &cFormatting: &6>prefix &a- &6displays username.]"));
                }else {
                    StringBuilder str = new StringBuilder();

                    for (int j = 0; j < args.length; j++) {
                        str.append(args[j] + " ");
                    }
                    String alert = str.toString();
                    if(alert.contains("-help"))
                    {
                        desc(sender,main);
                        return true;
                    }
                    alert = alert.replace("&", "§");
                    alert = alert.replace(">prefix",ChatColor.RED + " "+sender.getName() +ChatColor.DARK_GRAY + ">" + ChatColor.WHITE);
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("AlertPrefix")) + " " + alert);
                    return true;
                }
            }
        }
        return true;
    }
}
