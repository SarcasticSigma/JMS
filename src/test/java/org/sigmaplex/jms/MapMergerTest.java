package org.sigmaplex.jms;

import org.junit.jupiter.api.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;
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
        images.add(new MapPart(new File("src/test/resources/left.png")));
        images.add(new MapPart(new File("src/test/resources/right.png")));
        System.out.println(images);


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