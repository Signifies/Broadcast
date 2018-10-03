package Utilities;

import com.sun.org.apache.regexp.internal.RE;
import org.bukkit.util.permissions.BroadcastPermissions;

import java.util.ArrayList;

/**
 * Created by ES359 on 6/5/16.
 */
public class Menus extends BroadcastUtils
{

    private String menu;
    private String pref;
    private String suff;
    private String sep;
    private String colorHelp = "&7View color code help here: &b&nhttp://minecraftcolorcodes.com/";
    public Menus(String menuHeader, String prefix, String suffix, String separator){
        menu = color(menuHeader);
        pref = color(prefix);
        suff = color(suffix);
        sep = color(separator);
    }


    public ArrayList<String> commandList()
    {
        ArrayList<String> value = new ArrayList<>();

        value.add(menu+ getPrefix().replace(":","") +menu);
        value.add("");
        value.add("&f&l     -- &aHelp Menu &f&l--");
        value.add("");
        value.add("&b/broadcast <msg> &f- Broadcasts a message globally -");
        value.add(pref+" Broadcast Arguments "+suff+" &b/broadcast &7[-reload] "+sep+" &7[-about] "+sep+" &7[-ver] "+sep+" &7[-help]");
        value.add("&b/sb <msg> &f- Broadcasts a message to all staff memebers online.");
        value.add(pref+"Staff broadcast Arguments "+suff+"&b/sb &7[-toggle] &f"+sep+" Toggles receiving staff notifications.");
        value.add(pref+"&b/shout <msg> &f- Broadcasts a global message with a timed delay in seconds.");
        value.add(pref+"&b/report <player> <msg> &f- Reports a player to the whole server or just admins.");
        value.add("");
        value.add(menu+"&cPermissions"+menu);
        value.add("broadcast.use "+sep+" Allows usage of the /broadcast cmd.");
        value.add("broadcast.staff "+sep+" Allows usage of the /sb cmd.");
        value.add("broadcast.staff.receive "+sep+" Allows a staff member to receive staff notifications.");
        value.add("broadcast.shout "+sep+" Allows a user to use /shout");
        value.add("broadcast.reload "+sep+" Allows a &cStaff &amember to reload the configuration.");
        value.add("broadcast.report");
        value.add("broadcast.reports.receive");
        value.add("roadcast.reports.command.bypass");
        value.add("&8--------------------------------------------");
        return value;
    }

    public ArrayList<String> commandBroadcast()
    {
        ArrayList<String> value = new ArrayList<>();
        value.add(menu+ getPrefix().replace(":","") +menu);
        value.add("");
        value.add(pref+"&b/broadcast <msg> &f- Broadcasts a message globally -");
        value.add(pref+"Broadcast Arguments "+suff+" &b/broadcast &7[-reload] "+sep+" &7[-about] "+sep+" &7[-ver] "+sep+" &7[-help]");
        value.add(pref+"&fPermissions: &b" + Permissions.BROADCAST_PERM +sep+Permissions.BROADCAST_RELOAD+"/*Soon permission for perworld broadcast.*/");
        value.add("&8--------------------------------------------");
        return value;
    }

    public ArrayList<String> commandStaffBroadcast()
    {
        ArrayList<String> value = new ArrayList<>();
        value.add(menu+ getPrefix().replace(":","") +menu);
        value.add("");
        value.add("&b/sb <msg> &f- Broadcasts a message to all staff members online.");
        value.add(pref+"Staff broadcast Arguments "+suff+"&b/sb &7[-toggle] &f"+sep+" Toggles receiving staff notifications.");
        value.add(pref+"&fPermissions: &b" + Permissions.BROADCAST_STAFF_PERM +sep+Permissions.BROADCAST_STAFF_RECEIVE_PERM+"");
        value.add("&8--------------------------------------------");
        return value;
    }

    public ArrayList<String> commandReport()
    {
        ArrayList<String> value = new ArrayList<>();
        value.add(menu+ getPrefix().replace(":","") +menu);
        value.add("");
        value.add(pref+"&b/report <player> <msg> &f- Reports a player to the whole server or just admins.");
        value.add(pref+"&fPermissions: &b" + Permissions.BROADCAST_REPORT_COMMAND+"");
        value.add("&8--------------------------------------------");
        return value;
    }

    public ArrayList<String> commandShout()
    {
        ArrayList<String> value = new ArrayList<>();
        value.add(menu+ getPrefix().replace(":","") +menu);
        value.add("");
        value.add(pref+"&b/shout <msg> &f- Broadcasts a global message with a timed delay in seconds.");
        value.add(pref+"&fPermissions: &b" + Permissions.BROADCAST_SHOUT_PERM+"");
        value.add("&8--------------------------------------------");
        return value;
    }


    public ArrayList<String> shoutFunctions()
    {
        ArrayList<String> value = new ArrayList<>();
        value.add(menu+ "&fShout Functions&r "+menu);
        value.add(pref+"&b#name" + suff +"&7Displays player's name in shout.");
        value.add(pref+"&b#location" + suff +"&7Will display user location.");
        value.add(pref+"&b#uuid" + suff +"&7 Displays user's UUID.");
        value.add(pref+"&b#world" + suff +"&7Displays the players current world.");
        value.add(pref+"&b#exp" + suff +"Displays players Exp level.");
        value.add(pref+"&aFor permissions, check the spigot page." + suff +"");
        return value;
    }

}
