package me.signifies.Broadcast;

import Utilities.BroadcastUtils;
import Utilities.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 4/15/15.
 */
public class ModBroadcastCommand extends BroadcastUtils implements CommandExecutor {

    private Broadcast instance;

    public ModBroadcastCommand(Broadcast instance) {
        this.instance = instance;
    }



    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {


            if(!sender.hasPermission(Permissions.BROADCAST_STAFF_PERM)){
                sender.sendMessage(color(this.instance.getConfig().getString("Messages.modbroadcastCmdMsg")));
            }else {
                if(args.length < 1) {

                    sendText(instance.getMenus().commandStaffBroadcast(),sender);
                        if(instance.getNotifications().contains(sender.getName()))
                        {
                            sender.sendMessage(color(instance.getConfig().getString("Messages.warning")));
                        }
                }else {
                    if(args.length > 0) {

//                 TODO       instance.getNotifications().add(sender.getName());
                        StringBuilder str = new StringBuilder();

                        for (int j = 0; j < args.length; j++) {
                            str.append(args[j] + " ");
                        }
                        String alert = str.toString();
                        alert = alert.replace("&", "§");

                        String value = ChatColor.translateAlternateColorCodes('&',this.instance.getConfig().getString("Broadcast-settings.mod-broadcast.format"));

                        value = value.replace("%message%",alert);
                        value = value.replace("%username%",sender.getName());

                        if(alert.contains("-toggle"))
                        {
                            //TODO implement toggle.

                            if(!instance.getNotifications().contains(sender.getName()))
                            {
                                instance.getNotifications().add(sender.getName());
                                sender.sendMessage(color(instance.getConfig().getString("Messages.notifiy-disabled")));
                            }else
                            {
                                instance.getNotifications().remove(sender.getName());
                                sender.sendMessage(color(instance.getConfig().getString("Messages.notifiy-enabled")));
                            }

                        }else
                        {
                            for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
                                if(staff.hasPermission(Permissions.BROADCAST_STAFF_RECEIVE_PERM))
                                {
                                    if(!instance.getNotifications().contains(staff.getName()))
                                    {
                                        staff.sendMessage(value);
                                        broadcastSound(instance.getConfig().getString("Message-sounds.modbroadcast-sound"),instance.getConfig().getBoolean("Broadcast-settings.mod-broadcast.Sound-on-broadcast"));
                                    }
                                }
                            }
                            Bukkit.getServer().getConsoleSender().sendMessage(value);
                        }
                    }
                }
            }
        return true;
    }

}
