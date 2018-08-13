package cloud.chew.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class MySql {

    public static String host = "localhost";
    public static String port = "3306";
    public static String database = "test";
    public static String username = "java";
    public static String password = "3605679510Chew";
    public static Connection con;

    static ConsoleCommandSender console = Bukkit.getConsoleSender();

    // connect
    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                console.sendMessage("Connecting");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // disconnect
    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                console.sendMessage("Disconnecting");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // isConnected
    public static boolean isConnected() {
        return (con == null ? false : true);
    }

    // getConnection
    public static Connection getConnection() {
        return con;
    }
    
}