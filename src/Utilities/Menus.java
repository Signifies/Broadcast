package Utilities;

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
    @Deprecated
    public Menus() { }

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
//        value.add("&f&l   -- &bCommands &f&l--");
//        value.add("");
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
}
