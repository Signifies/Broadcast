package Utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    private String prefix = color("&2&l[&b&lBroadcast&2&l]&6: &f");
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


    public void logToConsole(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void desc(CommandSender sender, Plugin instance)
    {
        PluginDescriptionFile pdfFile;
        pdfFile = instance.getDescription();
        sender.sendMessage(color("&2========== " + getPrefix().replace(":","") + "&2=========="));
        sender.sendMessage(color("&7[&9" + pdfFile.getName() + "&7] &6Created by, &b&lES359&6."));
        sender.sendMessage(color("&2" + pdfFile.getDescription() + "&2."));
        sender.sendMessage(color("&bWebsite: &e&l" + pdfFile.getWebsite()));
        sender.sendMessage(color("     &6&l>>>&2&l===============&6&l<<<\t"));
    }


    public String color(String message) {

     return   message.replaceAll("&", "§");
    }

    public void plugin_help(CommandSender sender)
    {

    }

}
