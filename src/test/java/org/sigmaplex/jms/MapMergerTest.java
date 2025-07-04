package org.sigmaplex.jms;

import org.junit.jupiter.api.io.TempDir;
import org.sigmaplex.jms.Map.MapMerger;
import org.sigmaplex.jms.Map.MapPart;

import org.junit.jupiter.api.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.jupiter.api.Assertions.*;


class MapMergerTest {

    MapMerger mm;
    public final String basePath = "/journeymap/data/mp/Server_79fc0cc3~c72c~40ab~89b7~da29e48a592f/overworld/";
    String tempDir;


    public  void copyFolder(Path src, Path dest) throws IOException {
        try (Stream<Path> stream = Files.walk(src)) {
            stream.forEach(source -> copy(source, dest.resolve(src.relativize(source))));
        }
    }

    private void copy(Path source, Path dest) {
        try {
            Files.copy(source, dest, REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @BeforeEach
    void setup(@TempDir Path tempDir) throws IOException {
        this.mm = new MapMerger();
        this.tempDir = tempDir.toString();
        copyFolder(Path.of("src/test/resources/minecraft"), tempDir);
    }

    @Test
    public void merge(){
        ArrayList<MapPart> images = new ArrayList<>();
        images.add(new MapPart(
                new File(this.tempDir + basePath + "day/client1/0,6.png"),
                new File(this.tempDir + basePath + "cache/client1/r.0.6.mca"))
        );
        images.add(new MapPart(
                new File(this.tempDir + basePath + "day/client2/0,6.png"),
                new File(this.tempDir + basePath + "cache/client2/r.0.6.mca"))
        );
        try{
            BufferedImage expected = ImageIO.read(new File("src/test/resources/merged.png"));
            BufferedImage actual = mm.merge(images).getImage();
            assertTrue(imageEquals(expected, actual));
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    public boolean imageEquals(BufferedImage image1, BufferedImage image2){
        boolean result = true;
        for(int x = 0; x < image1.getWidth(); x++){
            for(int y = 0; y < image1.getHeight(); y++){
                if(image1.getRGB(x, y) != image2.getRGB(x, y)){
                    result = false;
                    break;
                }
            }
            if(!result){
                break;
            }
        }
        return result;
    }
}