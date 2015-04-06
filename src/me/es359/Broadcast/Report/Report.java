package me.es359.Broadcast.Report;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ES359 on 4/3/15.
 */
public class Report implements CommandExecutor{

    public CreateSQLTables table = new CreateSQLTables();

    private String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
    private String[] authList = new String[10];
    public String getAuthor(){
        authList[0] = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
        return author;
    }

    /**
     * Next feature to implement will be a function to ban a user from submitting a bugreport.
     *
     *
     */


    boolean log = true;
    private SQL sql;

    public void runConnection() {
        sql = new SQL("107.170.21.151","Logger","REQUEST1", "Logs");
        try{
            table.createTable(sql,"CREATE TABLE IF NOT EXISTS user_reports (id INT PRIMARY KEY AUTO_INCREMENT, plugin varchar(25),  name varchar(50), UUID varchar(50), report varchar(250), stamp TIMESTAMP, status varchar(350));");
            this.table.createTable(sql,"CREATE TABLE report_bans (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Name varchar(25), UUID varchar(45), banned_by varchar(35), ban_reason varchar(250)); ");
            if(log) {
                //logToConsole("Table created.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //107.170.21.151 Logger REQUEST1 Logs

    public SQL getAccess(){
        return sql;
    }
    public void connectionExists(){
        sql = new SQL("107.170.21.151","Logger","REQUEST1", "Logs");

    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {



        if(!(sender instanceof Player)) {
            sender.sendMessage("Cannot be used from console.");
            return true;
        }

        runConnection();


        Player p = (Player)sender;

        ReportBans bans = new ReportBans();

        String name = p.getName();
        String uuid = ""+p.getUniqueId();
        String plugin = "Broadcast";

        if(cmd.getName().equals("br-bugreport")) {

            bans.checkBanned(sql,p);
            if(bans.getStatus() == true) {

               // bans.checkBanned(sql,p);

              // p.sendMessage(bans.returnReason());
               p.sendMessage(ChatColor.RED +"You have been banned from submitting reports, " + ChatColor.DARK_RED+p.getName() + ".");
            }else {
                if(args.length < 1) {
                    p.sendMessage("Command usage: /br-bugreport <message>");

                }else if(args.length > 0) {
                    StringBuilder str = new StringBuilder();

                    for(int j = 0; j <args.length; j++) {
                        str.append(args[j] + " ");
                    }

                    String msg = str.toString();

                    msg = msg.replace("&", "§");

                    try {
                        PreparedStatement statement = this.sql.getConnection().prepareStatement("INSERT INTO user_reports (plugin,name,UUID,report) VALUES (?,?,?,?)");
                        statement.setString(1,plugin);
                        statement.setString(2,name);
                        statement.setString(3,uuid);
                        statement.setString(4,msg);
                        // statement.setString(5,""+stamp.getStamp());

                        statement.execute();
                        statement.close();

                        p.sendMessage(ChatColor.GREEN+"Your report was filed! Thanks for your help - The Dev's.");

                    }catch (SQLException e) {
                        e.printStackTrace();
                        p.sendMessage(ChatColor.RED+"Error - Report couldn't be filed!");
                    }
                    return true;
                }
            }
        }
        return true;
    }

}
