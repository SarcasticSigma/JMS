package org.sigmaplex.jms.util;

import org.sigmaplex.jms.Map.MapPart;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DAO {
    private static final String connectionString = "jdbc:sqlite:map.db";

    public DAO() {

    }



    public void create(MapPart m) {
        try (Connection c = DriverManager.getConnection(connectionString)) {
            PreparedStatement p = c.prepareStatement("INSERT INTO maps (mapname, cachename, levelname, maptype, hash) VALUES (?, ?, ?, ?, ?)");

            p.setString(1, m.getMapname());
            p.setString(2, m.getCachename());
            p.setString(3, m.getLevelType());
            p.setString(4, m.getMapType());
            p.setString(5, m.getHash(m.IMAGE));
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<HashMap<String, String>> getByHash(String s) {
        ArrayList<HashMap<String, String>> results = null;

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<HashMap<String, String>> list(){
        ArrayList<HashMap<String, String>> results;
        try (Connection c = DriverManager.getConnection(connectionString)) {
            PreparedStatement p = c.prepareStatement("SELECT * FROM maps");
            results = structureResults(p.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(results == null){
            throw new RuntimeException("Error retrieving results from database!");
        }
        return results;
    }


    /* Deletes all the data from the database */
    public void truncate(){
          try (Connection c = DriverManager.getConnection(connectionString)) {
            PreparedStatement p = c.prepareStatement("DELETE FROM maps");
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<HashMap<String, String>> structureResults(ResultSet rs) throws SQLException {
        ArrayList<HashMap<String, String>> resultList = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()){
            HashMap<String, String> row = new HashMap<>();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                row.put(rsmd.getColumnName(i), rs.getString(i));
            }

            resultList.add(row);
        }
        return resultList;
    }
}
