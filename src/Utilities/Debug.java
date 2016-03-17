package Utilities;

import me.es359.Broadcast.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
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


    /**
     * Simple debug flag enabler.
     * @param sender
     * @param args
     */
    static public void debugToggle(CommandSender sender, String[] args)
    {
        if(args.length > 1 && args[0].equalsIgnoreCase("-debug"))
        {

            /**
             * REPLACE MAINCLASS WITH YOUR MAINCLASS.
             *
             * EXAMPLE: <MAINCLASS>.DEBUG.
             *
             * Make sure to add:
             * static public DEBUG = false; // To your main class.
             *
             *
             */

            Broadcast.DEBUG = Boolean.parseBoolean(args[1]);
            sender.sendMessage(utils.color("[&4DEBUG&f] &c--> &7You have set Debug status to &4&l: " + Broadcast.DEBUG));
        }
    }
}