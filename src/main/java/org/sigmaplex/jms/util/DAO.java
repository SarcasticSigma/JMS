package org.sigmaplex.jms.util;

import org.sigmaplex.jms.Map.MapPart;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.HashMap;

public class DAO {
    private static final String connectionString = "jdbc:sqlite:map.db";

    public DAO() {

    }

    public void getByHash(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void create(MapPart m) {
        try (Connection c = DriverManager.getConnection(connectionString); Statement s = c.createStatement()) {
            PreparedStatement p = c.prepareStatement("INSERT INTO maps (mapname, cachename, levelname, maptype, hash) VALUES (?, ?, ?, ?, ?)");

            p.setString(1, m.getMapname());
            p.setString(2, m.getCachename());
            p.setString(3, m.getLevelname());
            p.setString(4, m.getMaptype());
            p.setString(5, m.getHash(m.IMAGE));
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
