package org.sigmaplex.jms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Common {
    private static final String URL = "jdbc:sqlite:map.db";

    public static void createServer() {


        try (
                Connection connection = DriverManager.getConnection(URL);
                Statement c = connection.createStatement()
        ) {
            c.executeUpdate("DROP TABLE IF EXISTS Maps");
            c.executeUpdate("""
                        CREATE TABLE Maps(
                            mapname varchar NOT NULL,
                            cachename varchar ,
                            levelname varchar NOT NULL,
                            maptype varchar NOT NULL,
                            hash varchar NOT NULL,
                            CONSTRAINT PK_Maps PRIMARY KEY (mapname,levelname,maptype))
                    """);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void createZipData(File map, File region) throws IOException {
        // DONT USE FOR TESTS (BROKEN DIR)
        // String s = FMLPaths.GAMEDIR.get() + "/world/data/jmssync.zip";
        File zip = new File("run/world/data/jmssync.zip");
        try (
                ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zip))
        ) {
            outputStream.putNextEntry(new ZipEntry(map.getName()));
            outputStream.putNextEntry(new ZipEntry(region.getName()));
        } catch (FileNotFoundException e) {
            System.out.println("Where zip ?");
        }
    }

    public static void unZip() {

    }

}
