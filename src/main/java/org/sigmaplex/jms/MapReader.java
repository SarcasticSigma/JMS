package org.sigmaplex;

import java.io.File;
import java.util.ArrayList;

public class MapReader {
    final String MAP_PATH = "C:\\Projects\\Java\\JMS\\src\\main\\resources\\mapdata\\data\\mp\\Server_79fc0cc3~c72c~40ab~89b7~da29e48a592f\\overworld\\day";
    File directory = new File(MAP_PATH);

    public ArrayList<MapPart> readMaps() {
        ArrayList<MapPart> parts = new ArrayList<>();
        if (directory.exists()) {
            File[] files = directory.listFiles();
            assert files != null;
            for(File file : files){
                if(file.isFile() && file.getName().endsWith(".png")){
                    parts.add(new MapPart(file));
                }
            }

        }
        return parts;
    }
}
