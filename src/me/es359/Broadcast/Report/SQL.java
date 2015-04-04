package me.es359.Broadcast.Report;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ES359 on 4/3/15.
 */
public class SQL {
    private String host,user,pass,database;


    public String getHost() {
        return host;
    }


    public String getUser() {
        return user;
    }


    public String getPassword() {
        return pass;
    }


    public String getDatabase() {
        return database;
    }


    public void setHost(String host) {
        this.host = host;
    }


    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }


    public void setDatabase(String database) {
        this.database = database;
    }


    public SQL() {}

    public Connection c;

    public SQL(String ip, String userName, String access, String db) {

        this.host = ip;
        this.user = userName;
        this.pass = access;
        this.database = db;



        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + access);
        } catch (Exception e) {
            e.printStackTrace();
            //logToConsole(connectionErrorInform());
        }
    }

    public Connection getConnection() {
        return c;
    }
}
