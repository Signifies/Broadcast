package me.es359.Broadcast.Report;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ES359 on 4/3/15.
 */
public class CheckReports implements CommandExecutor {
    public void authorCheck(SQL con,Player p) {


        try {
            Statement s = con.getConnection().createStatement();

            String query = "SELECT * FROM user_reports ;";

            ResultSet set = s.executeQuery(query);
            String header = ChatColor.GRAY +"Name           report    Status:\n--------------------------" ;
            p.sendMessage(header);
            while(set.next()){

                String result = ChatColor.AQUA+set.getString(1) +ChatColor.RESET+"  "+ set.getString(2) +"  :"+ ChatColor.GOLD +"  " + set.getString(3);
                p.sendMessage(result);
            }
            p.sendMessage("\n");
            p.sendMessage(ChatColor.GRAY + "Data displayed.");
        }catch (SQLException e) {
            e.printStackTrace();
            p.sendMessage(ChatColor.RED+"ERROR - No data found.");
        }

    }

    public void authorCheck(SQL con,Player p, String var) {

        try {
            Statement s = con.c.createStatement();

            String query = "SELECT * FROM user_reports WHERE id='"+var+"'";

            ResultSet set = s.executeQuery(query);
            String header = ChatColor.GRAY +"Name           report    Status:\n--------------------------" ;
            p.sendMessage(header);
            while(set.next()){

                String result = ChatColor.GOLD + "" +set.getString(1) + "  "+ ChatColor.AQUA+set.getString(3) +ChatColor.RESET+"  "+ set.getString(5) +"  :"+ ChatColor.GOLD +"  " + set.getString(7);

                p.sendMessage(result);
            }
            p.sendMessage("\n");
            p.sendMessage(ChatColor.GRAY + "Data displayed.");
        }catch (SQLException e) {
            e.printStackTrace();
            p.sendMessage(ChatColor.RED+"ERROR - No data found.");
        }

    }

    private Report report = new Report();
    private SQL sql;
    public void connection(){
        report.connectionExists();

        sql = report.getAccess();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

        connection();

        if(!(sender instanceof Player)) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        Player p = (Player)sender;


        if(cmd.getName().equalsIgnoreCase("br-check")){

            String uuid = ""+p.getUniqueId();

            if(!uuid.equals(report.getAuthor())){
                p.sendMessage("Unknown command. Type \"/help\" for help.");
                // p.sendMessage(ChatColor.AQUA+"只是一些中国没有概率");
            }else {
                if(args.length < 1) {
                    p.sendMessage(report.getMessage());
                    // ID:   USER:  STATUS:
                    //Show Following ?: 12    ES359  <STATUS MESSAGE>
                    authorCheck(sql,p);
                    p.sendMessage(ChatColor.GREEN + "Use /check <id> for more information about the report.");
                }else if(args.length > 0) {
                    p.sendMessage(report.getMessage());
                    String id = args[0];
                    authorCheck(sql, p, id);
                }
            }
        }
        return false;
    }
}
