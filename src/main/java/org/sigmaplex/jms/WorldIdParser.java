package org.sigmaplex.jms;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;


public class WorldIdParser {
    final String filepath = "/world/data/WorldUUID.dat";
    File target;

    WorldIdParser(Path baseDir) {
        this.target = new File(baseDir + filepath);
    }

    public String getId() {
        CompoundTag tag = null;
        String uuid = null;
        try {
            tag = NbtIo.readCompressed(this.target);
            tag = tag.getCompound("data");
            tag = tag.getCompound("WorldUUID");
            uuid = tag.getString("world_uuid");
        } catch (IOException e) {
            throw new RuntimeException("Could not read WorldUUID.dat");
        }
        return uuid;
    }
}
