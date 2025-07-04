package org.sigmaplex.jms.Map;

import net.minecraftforge.fml.loading.FMLPaths;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

/*
 * Merges map regions into another using a bitmap to copy the non-transparent regions filled regions i unfilled regions to filled regions
 * */
public class MapMerger {
    public MapMerger() {}

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
                if (!uniqueParts.containsKey(part.getHash(part.IMAGE))) {
                    uniqueParts.put(part.getHash(part.IMAGE), part);
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
