package org.sigmaplex.jms;

import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.loading.FMLPaths;
import org.checkerframework.checker.units.qual.A;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

/*
 * Merges map regions into another using a bitmap to copy the non-transparent regions filled regions i unfilled regions to filled regions
 * */
public class MapMerger {
    public MapMerger() {
        ArrayList<MapPart> images = new ArrayList<>();
        File journeyMapServers = new File(FMLPaths.GAMEDIR + "\\journeymap\\data\\mp\\");
        if (journeyMapServers.exists()) {
            File[] serverDirs = journeyMapServers.listFiles();
            for (File server : serverDirs) {
                File mapDir = new File(server.getPath() + "\\overworld\\day");
                File[] parts = mapDir.listFiles((dir, name) -> name.endsWith(".png"));

                assert parts != null;
                for (File part : parts) {
                    images.add(new MapPart(part));
                }
            }
        }

        int merged = 0;
        try {
            ImageIO.read(new File(""));
            merged = 1;
        } catch (IOException ignored) {
        }
    }


    public MapPart merge(ArrayList<MapPart> images) {
        MapPart result = null;

        if (images.isEmpty()) {
            throw new RuntimeException("There is are no images to merge.");
        } else if (images.size() == 1) {
            result = images.get(0);
        } else {
            // Find the unique images (we don't need to iterate over the same image twice)
            HashMap<String, MapPart> uniqueParts = new HashMap<>();
            for (MapPart part : images) {
                if (!uniqueParts.containsKey(part.getHash())) {
                    uniqueParts.put(part.getHash(), part);
                }
            }

            try{
                MapPart root = null;
                for(MapPart part : uniqueParts.values()) {
                    // If this is the first loop, set the root to the first item and proceed.
                    if(root == null){
                        root = part;
                    }else{
                        root.appendImage(part.getImage());
                    }
                    result = root;
                }
            }catch(IOException e){
                throw new RuntimeException("Missing image!");
            }
        }
        assert result != null;
        return result;
    }
}
