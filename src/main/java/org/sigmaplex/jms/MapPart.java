package org.sigmaplex;

import java.io.File;

public class MapPart {
    File file;
    MapPart(File file){
        this.file = file;
    }

    @Override
    public String toString() {
        return file.toString();
    }
}
