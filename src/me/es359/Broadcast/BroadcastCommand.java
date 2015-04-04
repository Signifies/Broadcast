package me.es359.Broadcast;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by ES359 on 4/3/15.
 */
public class BroadcastCommand implements CommandExecutor {

    public Main main;

    public BroadcastCommand(Main instance) {
        this.main= instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {




        if(cmd.getName().equalsIgnoreCase("Broadcast")) {
            if(!sender.hasPermission("Broadcast.use"))
            {
                sender.sendMessage(ChatColor.YELLOW +"You need the permission, Broadcast.use to complete that.");
            }else {
                if(args.length < 1) {
                    sender.sendMessage("/broadcast <message> [Color codes can be used inside Broadcast]");
                }else {
                    StringBuilder str = new StringBuilder();

                    for (int j = 0; j < args.length; j++) {
                        str.append(args[j] + " ");
                    }
                    String alert = str.toString();
                    alert = alert.replace("&", "§");
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("AlertPrefix")) + " " + alert);
                    return true;
                }
            }
        }
        return true;
    }
}
