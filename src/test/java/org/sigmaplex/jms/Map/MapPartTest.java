package org.sigmaplex.jms.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MapPartTest {
    MapPart mapPart;
    @BeforeEach
    public void setup(){
        String server = "Server_79fc0cc3~c72c~40ab~89b7~da29e48a592f";
        Path gameDir = new File("src/test/resources/minecraft").toPath();
        this.mapPart = new MapPart(0,5,gameDir, server);
    }

    @Test
    void getImageHash() {
        assertEquals("A46EEC773F9AF3A4AED339BF3EE147F2".toLowerCase(), mapPart.getHash(mapPart.IMAGE));
    }

    @Test
    void getCacheHash() {
        assertEquals("A99521043C1871260C6FAEBB7BCECE9C".toLowerCase(), mapPart.getHash(mapPart.CACHE));
    }

    @Test
    void getMapname() {
        assertEquals("0,5.png", mapPart.getMapname());
    }

    @Test
    void getCachename() {
        assertEquals("r.0.5.mca", mapPart.getCachename());
    }

    @Test
    public void getLevelname(){
        assertEquals("OVERWORLD", mapPart.getLevelType());
    }
    @Test
    public void getMaptype(){
        assertEquals("DAY", mapPart.getMapType());
    }
}