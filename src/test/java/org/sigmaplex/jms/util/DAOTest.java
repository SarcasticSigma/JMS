package org.sigmaplex.jms.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sigmaplex.jms.Map.MapPart;

import java.io.File;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest {
    DAO dao;

    @BeforeEach
    void setup() {
        this.dao = new DAO();
        this.dao.truncate();
    }

    @Test
    void create() {
        String server = "Server_79fc0cc3~c72c~40ab~89b7~da29e48a592f";
        Path gameDir = new File("src/test/resources/minecraft").toPath();
        MapPart mapPart = new MapPart(0, 5, gameDir, server);
        dao.truncate();
        dao.create(mapPart);
    }

    @Test
    void list() {
        String server = "Server_79fc0cc3~c72c~40ab~89b7~da29e48a592f";
        Path gameDir = new File("src/test/resources/minecraft").toPath();
        MapPart mapPart = new MapPart(0, 5, gameDir, server);
        dao.truncate();
        dao.create(mapPart);
        ArrayList<HashMap<String, String>> result = dao.list();
        assertEquals(1, result.size());
        HashMap<String, String> row = result.get(0);

        HashMap<String, String> expected = new HashMap<>();
        expected.put("hash", "a46eec773f9af3a4aed339bf3ee147f2");
        expected.put("maptype", "DAY");
        expected.put("levelname", "OVERWORLD");
        expected.put("cachename", "r.0.5.mca");
        expected.put("mapname", "0,5.png");
        for (String s : expected.keySet()) {
            assertEquals(expected.get(s), row.get(s));
        }
    }
}