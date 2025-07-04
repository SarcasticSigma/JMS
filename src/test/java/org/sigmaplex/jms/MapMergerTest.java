package org.sigmaplex.jms;

import org.sigmaplex.jms.Map.MapMerger;
import org.sigmaplex.jms.Map.MapPart;

import org.junit.jupiter.api.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class MapMergerTest {

    MapMerger mm;

    @BeforeEach
    void setUp() {
        this.mm = new MapMerger();
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    public void merge(){
        ArrayList<MapPart> images = new ArrayList<>();
        images.add(new MapPart(
                new File("src/test/resources/minecraft/journeymap/data/mp/Server_79fc0cc3~c72c~40ab~89b7~da29e48a592f/overworld/day/client1/0,6.png"),
                new File("src/test/resources/minecraft/journeymap/data/mp/Server_79fc0cc3~c72c~40ab~89b7~da29e48a592f/overworld/cache/client1/r.0.6.mca"))
        );
        images.add(new MapPart(
                new File("src/test/resources/minecraft/journeymap/data/mp/Server_79fc0cc3~c72c~40ab~89b7~da29e48a592f/overworld/day/client2/0,6.png"),
                new File("src/test/resources/minecraft/journeymap/data/mp/Server_79fc0cc3~c72c~40ab~89b7~da29e48a592f/overworld/cache/client2/r.0.6.mca"))
        );
        MapPart image = this.mm.merge(images);
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
            };
        }
        return result;
    }
}