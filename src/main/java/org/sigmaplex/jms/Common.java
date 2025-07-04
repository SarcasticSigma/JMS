package org.sigmaplex.jms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Common {
    private static final String URL = "jdbc:sqlite:map.db";

    public static void createServer(){


        try (
                Connection connection = DriverManager.getConnection(URL);
                Statement c = connection.createStatement()
        ) {
            c.executeUpdate("DROP TABLE IF EXISTS Maps");
            c.executeUpdate(
                "CREATE TABLE Maps(" +
                    "mapname varchar NOT NULL," +
                    "cachename varchar ,"+
                    "levelname varchar NOT NULL, " +
                    "maptype varchar NOT NULL," +
                    "hash varchar NOT NULL," +
                    "CONSTRAINT PK_Maps PRIMARY KEY (mapname,levelname,maptype));");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }
    //Could be unsafe
    public static void addMap(String fileName,String levelName, String mapType,String hash){
        fileName += ".png";
        String cacheName = "r."+fileName.replace(',','.')+".mca";
        try(Connection c = DriverManager.getConnection(URL);Statement s = c.createStatement()) {
            s.executeUpdate(
                    "insert into Maps " +
                        "(mapname,cachename, levelname, maptype, hash)" +
                        "values('"+ fileName+"','"+cacheName+"','"+levelName+"','"+mapType+"','"+hash+"')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
