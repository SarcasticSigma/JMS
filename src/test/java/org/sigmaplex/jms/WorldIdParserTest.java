package org.sigmaplex.jms;

import net.minecraft.nbt.CompoundTag;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class WorldIdParserTest {
    @Test
    void testGetId() {
        String id = new WorldIdParser(Path.of("src/test/resources/minecraft/")).getId();
        assertEquals("0b310c83-1c78-4713-98a4-3214691b9c86", id.toString());
    }   @Test
    void testGetIdMissingFile() {
        assertThrows(RuntimeException.class, () -> new WorldIdParser(Path.of("")).getId());
    }
}