package me.es359.Broadcast;

import Utilities.BroadcastUtils;
import Utilities.Debug;
import Utilities.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.HashMap;

/**
 * Created by ES359 on 9/17/16.
 */
public class ReportCommand extends BroadcastUtils implements CommandExecutor
{
    Broadcast instance;

    public ReportCommand(Broadcast main)
    {
        instance = main;
    }

    public HashMap<String, Long> cooldowns = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(color("&cNot for use by console."));
            return true;
        }

        Player p =(Player) sender;

        int cooldownTime = instance.getConfig().getInt("Report.delay");

        String cooldown_msg = instance.getConfig().getString("Report.delay-msg");
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

        if(cmd.getName().equalsIgnoreCase("report") && p.hasPermission(Permissions.BROADCAST_REPORT_COMMAND))
        {
            if(args.length == 0)
            {
                p.sendMessage(color("&6Usage: &7/report <&fplayer&7> <&creason&7>"));
            }else if(args.length == 1)
            {
                p.sendMessage(color("&6You must give a reason on why you are reporting a user."));
            }else if(args.length > 0)
            {
                Player target = Bukkit.getServer().getPlayer(args[0]);

                if(target == null)
                {
                    p.sendMessage(color("&6You cannot report a player that doesn't exist."));
                    return true;
                }

                StringBuilder str = new StringBuilder();

                for (int j = 0; j < args.length; j++) {
                    str.append(args[j] + " ");
                }
                String report = str.toString().replace(args[0], "");
                String value = instance.getConfig().getString("Report.format");
                value = value.replace("%reporter%",p.getName());
                value = value.replace("%rule_breaker%",target.getName());
                value = value.replace("%reason%",report);

                if(!p.hasPermission(Permissions.BROADCAST_REPORT_DELAY_BYPASS))
                {

                    if(instance.getConfig().getBoolean("Report.admins-only"))
                    {
                        for(Player staff : Bukkit.getOnlinePlayers())
                        {
                            if(staff.hasPermission(Permissions.BROADCAST_REPORT_RECIEVE) && !instance.getNotifications().contains(staff.getName()))
                            {
                                staff.sendMessage(color(value));
                            }
                        }
                    }else
                    {
                        Bukkit.getServer().broadcastMessage(color(value));
                    }
                    cooldowns.put(p.getName(), System.currentTimeMillis());


                }else if(!instance.getConfig().getBoolean("Report.admins-only"))
                {

                    if(instance.getConfig().getBoolean("Report.admins-only"))
                    {
                        for(Player staff : Bukkit.getOnlinePlayers())
                        {
                            if(staff.hasPermission(Permissions.BROADCAST_REPORT_RECIEVE) && !instance.getNotifications().contains(staff.getName()))
                            {
                                staff.sendMessage(color(value));
                            }
                        }
                    }else
                    {
                        Bukkit.getServer().broadcastMessage(color(value));
                    }

                }
            }
        }else
        {
            p.sendMessage(color(instance.getConfig().getString("Report.no-perms")));
        }
        return true;
    }
}

