package Utilities;

import java.util.ArrayList;

/**
 * Created by ES359 on 6/5/16.
 */
public class Menus extends BroadcastUtils
{
    public ArrayList<String> commandList()
    {
        ArrayList<String> value = new ArrayList<String>();

        value.add("&a---------- " + getPrefix().replace(":","") +"&a----------");
        value.add("");
        value.add("&f&l     -- &aHelp Menu &f&l--");
        value.add("");
//        value.add("&f&l   -- &bCommands &f&l--");
//        value.add("");
        value.add("&b/broadcast <msg> &f- Broadcasts a message globally -");
        value.add("&a>> Broadcast Arguments - &b/broadcast &7[-reload] &a|| &7[-about] &a|| &7[-ver] &a|| &7[-help]");
        value.add("&b/sb <msg> &f- Broadcasts a message to all staff memebers online.");
        value.add("&a>> Staff broadcast Arguments - &b/sb &7[-toggle] &f- Toggles receiving staff notifications.");
        value.add("&b/shout <msg> &f- Broadcasts a global message with a timed delay in seconds.");
        value.add("&b/report <player> <msg> &f- Reports a player to the whole server or just admins.");
        value.add("");
        value.add("&f&l     -- &cPermissions &f&l--");
        value.add("broadcast.use &a- Allows usage of the /broadcast cmd.");
        value.add("broadcast.staff &a- Allows usage of the /sb cmd.");
        value.add("broadcast.staff.receive &a- Allows a staff member to receive staff notifications.");
        value.add("broadcast.shout &a- Allows a user to use /shout");
        value.add("broadcast.reload &a- Allows a &cStaff &amember to reload the configuration.");
        value.add("broadcast.report");
        value.add("broadcast.reports.receive");
        value.add("roadcast.reports.command.bypass");
        value.add("&8--------------------------------------------");
        return value;
    }
}
