package Utilities;

import me.es359.Broadcast.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.net.URL;
import java.sql.SQLException;
import java.util.UUID;

public class BroadcastUtils {

    /**
     * Plugin prefix.
     */
    private String prefix = ChatColor.translateAlternateColorCodes('&',"&2&l[&b&lBroadcast&2&l]&6: &f");
    /**
     * Constant permission error.
     */
    private String permission = getPrefix()+color("&cUnknown command. Type \"/help\" for help.");

    private String donationURL = color("https://www.paypal.me/ES359");

    String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
    /**
     * Author.
     */
    boolean checkAuth(UUID user)
    {
        return user.toString().equals(author);
    }

    /**
     * Gets the set plugin prefix.
     *
     * @return
     */
    public String getPrefix()
    {
        return this.prefix;
    }

    /**
     *
     * @return Donation URL
     */
    public String getDonationURL()
    {
        return donationURL;
    }

    /**
     * Gets pre-defined permission error.
     * @return
     */
    public String getPermission()
    {
        return this.permission;
    }

    public void authorMessage(Plugin instance, Player player)
    {
           if(instance.getConfig().getBoolean("Author-msg"))
           {
                displayHelpMsg(player,"&8&l---------- [&a&lBroadcast&8&l] -----------","&6Custom &cBroadcast plugin for spigot! &bBy &1__ES","&3Use /broadcast -help for info!\n&6Plugin Site! &e&lhttp://www.spigotmc.org/resources/broadcast.5747/\n&8-------------------------");
           }
    }

    public void logToConsole(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    //http://minecraftcolorcodes.com/

    /**
     *  Displays plugin description Information.
     *
     * @param sender
     * @param
     *
     */
    public void desc(CommandSender sender, Broadcast main)
    {
        sender.sendMessage(color("&2========== " + getPrefix().replace(":","") + "&2=========="));
        sender.sendMessage(color("&7[&9" + main.pdfFile.getName() + "&7] &6Created by, &b&l" +main.pdfFile.getAuthors()+"&6."));
        sender.sendMessage(color("&2" + main.pdfFile.getDescription() + "&2."));
        sender.sendMessage(color("&bWebsite: &e&l" + main.pdfFile.getWebsite()));
        sender.sendMessage(color("&bList of sounds: &3&nhttps://gist.github.com/ES359/7aa8da5dbf88496e4098 - Configuration sounds... "));
        sender.sendMessage(color("&bMinecraft Color Codes: &a&nhttp://minecraftcolorcodes.com/"));
        sender.sendMessage(color("     &6&l>>>&2&l===============&6&l<<<\t"));
    }

    public void displayHelp(CommandSender sender, String title, String command, String info)
    {
        sender.sendMessage(color(title));
        sender.sendMessage("");
        sender.sendMessage(color(command));
        sender.sendMessage("");
        sender.sendMessage(color(info));
    }

    public void displayHelpMsg(Player player, String title, String body, String information)
    {
        player.sendMessage(color(title));
        player.sendMessage("");
        player.sendMessage(color(body));
        player.sendMessage(" ");
        player.sendMessage(color(information));
    }

    public String color(String message) {

     String msg =  message;
            msg = msg.replace("&", "§");
            msg = msg.replace("%prefix%",getPrefix());
       return msg;
    }

    /**
     *  A method of controlling exception messages in the console.
     *
     * @param e
     */
    public void exceptionDebug(Exception e)
    {
        if(Broadcast.DEBUG)
        {
            logToConsole("&cERROR: &3" +e.getMessage());
            e.printStackTrace();
        }else
        {
            logToConsole("&cERROR: &3" +e.getMessage());
        }
    }

    /**
     *  Sends configured sound to player...
     *  https://gist.github.com/ES359/7aa8da5dbf88496e4098 - Configuration sounds...
     *  List of sounds: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html
     *
     * @param sound
     * @param enabled
     */
    public void broadcastSound(String sound, boolean enabled)
    {
        if(enabled)
        {
            try
            {

                for(Player p : Bukkit.getServer().getOnlinePlayers())
                {
                    p.playSound(p.getLocation(), Sound.valueOf(sound),1,1);
                }
            }catch (NullPointerException e)
            {
                exceptionDebug(e);
            }
        }
    }

}
