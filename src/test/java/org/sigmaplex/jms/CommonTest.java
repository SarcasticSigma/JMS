package org.sigmaplex.jms;

import org.junit.jupiter.api.Test;


public class CommonTest {
    @Test
    public void createServerTest(){
        Common c = new Common();
        c.createServer();
    }
}