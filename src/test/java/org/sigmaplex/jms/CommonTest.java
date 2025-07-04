package org.sigmaplex.jms;

import org.junit.jupiter.api.Test;


public class CommonTest {
    @Test
    public void createServerTest(){

        Common.createServer();
    }
    @Test
    public void addToDatabase(){
        Common.addMap("1_1.png","overworld","day","dfhuisak809374hfkASWE74IHJ8W93EYRPA");
    }
}