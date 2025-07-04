package org.sigmaplex.jms;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


public class CommonTest {
    @Test
    public void createServerTest(){

        Common.createServer();
    }
    @Test
    public void createZip() throws IOException {
        File r = new File("run/journeymap/data/sp/New World/overworld/r.0.0.mca");
        File n = new File("run/journeymap/data/sp/New World/overworld/0,0.png");
        Common.createZipData(n,r);
    }
}