package org.sigmaplex.jms.Map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MapPart {
    final String IMAGE_FORMAT = "png";
    File file;
    MapPart(File file){
        if(!file.exists()){
            throw new IllegalArgumentException("The passed file does not exist!");
        }
        this.file = file;

    }

    public String getHash() {
        byte[] data;
        byte[] hash;
        try{
            data = Files.readAllBytes(this.file.toPath());
            hash = MessageDigest.getInstance("MD5").digest(data);
        }catch(NoSuchAlgorithmException | IOException e){
            throw new RuntimeException("Invalid configuration!");
        }

        return new BigInteger(1, hash).toString(16);
    }


    BufferedImage getImage() throws IOException {
        return ImageIO.read(this.file);
    }

    @Override
    public String toString() {
        return file.toString();
    }

    public void appendImage(BufferedImage image) throws IOException {
        BufferedImage root = this.getImage();
        for(int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                int root_alpha = getAlpha(root.getRGB(x, y));
                int current_alpha = getAlpha(image.getRGB(x, y));
                if(current_alpha != 0 && root_alpha == 0){
                    root.setRGB(x, y, image.getRGB(x, y));
                }
            }
        }

        ImageIO.write(root, this.IMAGE_FORMAT, this.file);
    }

    public int getAlpha(int argb){
        return (argb >> 24) & 0xff;

    }
}
