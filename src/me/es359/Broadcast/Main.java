package me.es359.Broadcast;

import me.es359.Broadcast.Report.CheckReports;
import me.es359.Broadcast.Report.DeleteReport;
import me.es359.Broadcast.Report.GetReports;
import me.es359.Broadcast.Report.Report;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ES359 on 4/3/15.
 */
public class Main extends JavaPlugin
{

    public void onEnable()
    {

        //PluginManager pm = Bukkit.getServer().getPluginManager();

        this.getCommand("Broadcast").setExecutor(new BroadcastCommand(this));
        this.getCommand("br-bugreport").setExecutor(new Report());
        this.getCommand("br-reports").setExecutor(new GetReports());
        this.getCommand("br-check").setExecutor(new CheckReports());
        this.getCommand("br-deletereport").setExecutor(new DeleteReport());

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

    }

}
