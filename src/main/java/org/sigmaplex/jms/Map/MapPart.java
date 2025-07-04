package org.sigmaplex.jms.Map;

import net.minecraftforge.fml.loading.FMLPaths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class MapPart {
    private static final String IMAGE_FORMAT = "png";
    private int x;
    private int y;
    public TYPE IMAGE = TYPE.IMAGE;
    public TYPE CACHE = TYPE.CACHE;
    File imageFile;
    File cacheFile;

    public enum TYPE {
        IMAGE,
        CACHE
    }

    public MapPart(int x, int y, Path minecraftDirectory, String server) {
        String imageLocation = String.format("%s/journeymap/data/mp/%s/overworld/day/%d,%d.png", minecraftDirectory, server, x, y);
        String cacheLocation = String.format("%s/journeymap/data/mp/%s/overworld/cache/r.%d.%d.mca", minecraftDirectory, server, x, y);
        this.imageFile = new File(imageLocation);
        this.cacheFile = new File(cacheLocation);
        constructorChecks();
    }


    public MapPart(int x, int y, String server) {
        this(x, y, FMLPaths.GAMEDIR.get(), server);
    }

    // This is for testing, not intended for public usage.
    public MapPart(File image, File cache) {
        constructorChecks();
    }

    private void constructorChecks(){
        if (!this.imageFile.exists()) {
            throw new RuntimeException(String.format("Invalid mapPart! Image for (%d, %d) doesn't exist at %s!", x, y, imageFile.getAbsolutePath()));
        }
        if (!this.cacheFile.exists()) {
            throw new RuntimeException(String.format("Invalid mapPart! Cache for (%d, %d) doesn't exist at %s!", x, y, cacheFile.getAbsolutePath()));
        }
    }


    public String getHash(TYPE t) {
        byte[] data;
        byte[] hash;
        try {
            File file;
            if (t == TYPE.IMAGE) {
                file = imageFile;
            } else {
                file = cacheFile;
            }

            data = Files.readAllBytes(file.toPath());
            hash = MessageDigest.getInstance("MD5").digest(data);
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException("Invalid configuration!");
        }

        return new BigInteger(1, hash).toString(16);
    }


    public BufferedImage getImage() throws IOException {
        return ImageIO.read(this.imageFile);
    }


    @Override
    public String toString() {
        return this.imageFile.getAbsolutePath();
    }


    public void appendImage(BufferedImage image) throws IOException {
        BufferedImage root = this.getImage();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int root_alpha = getAlpha(root.getRGB(x, y));
                int current_alpha = getAlpha(image.getRGB(x, y));
                if (current_alpha != 0 && root_alpha == 0) {
                    root.setRGB(x, y, image.getRGB(x, y));
                }
            }
        }

        ImageIO.write(root, this.IMAGE_FORMAT, this.imageFile);
    }


    public int getAlpha(int argb) {
        return (argb >> 24) & 0xff;
    }


    public String getMapname() {
        return this.imageFile.getName();
    }


    public String getCachename() {
        return this.imageFile.getPath();
    }


    public String getLevelname() {
        return "";
    }


    public String getMaptype() {
        return "";
    }


    public boolean equals(MapPart part) {
        return part.imageFile == this.imageFile &&
                part.cacheFile == this.cacheFile &&
                Objects.equals(part.getHash(TYPE.IMAGE), getHash(TYPE.IMAGE)) &&
                Objects.equals(part.getHash(TYPE.CACHE), getHash(TYPE.CACHE));
    }
}
