package org.sigmaplex.jms;

import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraftforge.gametest.GameTestHolder;

@GameTestHolder
public class ModTests {
    @GameTest(templateNamespace = "jms")
    public static void test1(){
        Server.parseDatFile();
    }
}
