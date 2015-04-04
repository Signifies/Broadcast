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
public class DeleteReport implements CommandExecutor {
    private  void deleteReport(SQL con,Player p, String var) {
        try {
            PreparedStatement statement = con.getConnection().prepareStatement("DELETE FROM user_reports WHERE id='"+var+"';");
            statement.execute();
            statement.close();
            p.sendMessage(ChatColor.RED+"Deleted Report ID, " + var + ".");
        }catch (SQLException e) {
            e.printStackTrace();
            p.sendMessage(ChatColor.RED+"ERROR - Deletion didn't work..");
        }
    }

    private SQL sql;
    Report report = new Report();
    private void connection() {
        report.connectionExists();
        sql = report.getAccess();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
        connection();
        if (!(sender instanceof Player)) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        Player p = (Player)sender;

        if (cmd.getName().equalsIgnoreCase("br-deletereport")) {

            String uuid = ""+p.getUniqueId();

            if(!uuid.equals(report.getAuthor())) {
                p.sendMessage("Unknown command. Type \"/help\" for help.");
            }else {
                if (args.length < 1) {
                    p.sendMessage(ChatColor.RED +"/delete <id> [Use /check to check reports.]");
                }else if(args.length == 1) {
                    String id = args[0];
                    deleteReport(sql,p,id);
                }
            }
        }
        return false;
    }
}
