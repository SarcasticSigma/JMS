package org.sigmaplex.jms;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
* Represents a client whom subscribes to the JM server for syncing.
* */

public class Client {

    public Statement createServer(){
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:map.db");
                Statement command = connection.createStatement()
        ) {
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
