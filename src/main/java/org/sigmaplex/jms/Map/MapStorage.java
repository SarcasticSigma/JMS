package org.sigmaplex.jms.Map;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MapStorage {

    public static ResultSet getFiles(String hash){
        var url = "jdbc:sqlite:../files.db";
        ResultSet result = null;

        try (var conn = DriverManager.getConnection(url)) {
            System.out.println("Connection to SQLite has been established.");
            Statement s = conn.createStatement();
            result = s.executeQuery("SELECT * FROM maps");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("Error accessing local database");
        }

        return result;
    }


}
