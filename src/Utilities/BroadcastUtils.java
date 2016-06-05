package Utilities;

import me.es359.Broadcast.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 *
 * @author __ES (ES359) of Plugin, Broadcast.
 * All the code in this plugin, and related is the property
 * of of it's creator. You may decompile this resource at will, and if you use a specific section of code unique
 * to this plugin, please make sure I __ES, get credited.
 * Developers that contributed to this resource, will be mentioned under authors in the plugin.yml file.
 *
 */


public class BroadcastUtils {

    /**
     * Hard coded version ID. Because I forget.
     */
    private final String VERSION_ID = "3.0.2 6/5/16";

    /**
     * Plugin prefix.
     */
    private String prefix = ChatColor.translateAlternateColorCodes('&',"&2&l[&b&lBroadcast&2&l]&6: &f");
    /**
     * Constant permission error.
     */
    private String permission = getPrefix()+color("&cUnknown command. Type \"/help\" for help.");
    public String getPermission()
    {
        return this.permission;
    }

    private String donationURL = color("https://www.paypal.me/ES359");

    /**
     * @return Donation URL
     */
    public String getDonationURL()
    {
        return donationURL;
    }

    /**
     * Author.
     */
    String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";

    public boolean checkAuth(UUID user)
    {
        return user.toString().equals(author);
    }

    /**
     *  Informs author that plugin is being used by server.
     *
     * @param p
     */
    public void displayAuthInfo(Player p)
    {
        if(checkAuth(p.getUniqueId()))
        {
            p.sendMessage(color("&a&l&oHello, &7"+ p.getName() +"\n &aThis server is using " + getPrefix() + "&r\n&cVersion ID: &7" +VERSION_ID));
        }
    }
    /**
     *  Returns this plugins version.
     */
    public String getPluginVersion(Broadcast main, CommandSender sender)
    {
        return color("&fHello, &a&n"+sender.getName() +".&r\nYou are currently running version &b&n"+main.pdfFile.getVersion() + "&r of &e&n"+main.pdfFile.getName() +"&r\n \n&6Your server is running version &c&n"+ main.getServer().getBukkitVersion());
    }

    /**
     *
     * Functions to run inside author toggle.
     *
     */
    public void authorToggle(Broadcast main, Player author )
    {
        if(!main.getAuthor().contains(author.getUniqueId()))
        {
            displayAuthInfo(author);
        }
    }

    /**
     * Displays Author message to players.
     * @param instance
     * @param player
     */
    @Deprecated
    public void authorMessage(Plugin instance, Player player)
    {
        if(instance.getConfig().getBoolean("Author-msg"))
        {
            displayHelpMsg(player,"&8&l---------- [&a&lBroadcast&8&l] -----------","&6Custom & plugin for spigot! &bBy &1__ES","&3Use /broadcast -help for info!\n&6Plugin Site! &e&lhttp://www.spigotmc.org/resources/broadcast.5747/\n&8-------------------------");
        }
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
     * Gets pre-defined permission error.
     * @return
     */

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
        sender.sendMessage(color("&9If you like my work, you can support me by donating &ahere: " +getDonationURL()));
        sender.sendMessage(color("&8-----------------------------"));
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
     *  This method was created for the plugin Broadcast.
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

    public void sendText(ArrayList<String> text, CommandSender sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%player%",sender.getName());
            sender.sendMessage(color(txt));
        }
    }



}
