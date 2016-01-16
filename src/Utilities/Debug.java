package Utilities;

import me.es359.Broadcast.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Debug  {
static BroadcastUtils utils = new BroadcastUtils();
       static public String FAILED_ACTION = "[FAILED ACTION] ";
       static public String ACTION = "[ACTION] ";
       static public String LOG = "[LOG] ";
       static public String SEVERE = "[SEVERE] &c";

   static public void debugEnabled()
    {
        if(Broadcast.DEBUG)
        {
            System.out.println(Debug.LOG + " Debugging is enabled...");
        }else
        {
            System.out.println(Debug.LOG +"Debugging is " + Broadcast.DEBUG);
        }
    }

    static public void log(String message)
    {
        if(Broadcast.DEBUG)
        {
            Bukkit.getServer().getConsoleSender().sendMessage( message );
        }
    }

    static public void log(Player p, String message)
    {
        if(Broadcast.DEBUG)
        {
            p.sendMessage(utils.color(message));
        }
    }


}