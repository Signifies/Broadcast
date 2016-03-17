package me.es359.Broadcast;

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

    private Broadcast main;

    public ModBroadcastCommand(Broadcast instance) {
        this.main = instance;
    }



    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

        if(cmd.getName().equalsIgnoreCase("sb")) {
            if(!sender.hasPermission(Permissions.BROADCAST_STAFF_PERM)){
                sender.sendMessage(color(this.main.getConfig().getString("Messages.modbroadcastCmdMsg")));
            }else {
                if(args.length < 1) {

                    displayHelp(sender, "&8========== [&b&lHelp&8] &8==========", "&a&o/sb <message> &a- &c" +Permissions.BROADCAST_STAFF_PERM +"||" +Permissions.BROADCAST_STAFF_RECEIVE_PERM, "&1C&2o&3l&4o&5r &cFormatting:\n" +
                            "&8View color code help here: &b&nhttp://minecraftcolorcodes.com/");

                }else {
                    if(args.length > 0) {

                        StringBuilder str = new StringBuilder();

                        for (int j = 0; j < args.length; j++) {
                            str.append(args[j] + " ");
                        }
                        String alert = str.toString();
                        alert = alert.replace("&", "§");

                        String value = ChatColor.translateAlternateColorCodes('&',this.main.getConfig().getString("Broadcast-settings.mod-broadcast.format"));

                        value = value.replace("%message%",alert);
                        value = value.replace("%username%",sender.getName());

                        for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
                            if(staff.hasPermission(Permissions.BROADCAST_STAFF_RECEIVE_PERM)) {
                                staff.sendMessage(value);
                                broadcastSound(main.getConfig().getString("Message-sounds.modbroadcast-sound"),main.getConfig().getBoolean("Broadcast-settings.mod-broadcast.Sound-on-broadcast"));
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
