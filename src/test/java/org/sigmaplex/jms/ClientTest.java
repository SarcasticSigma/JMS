package org.sigmaplex.jms;

import org.junit.jupiter.api.Test;


public class ClientTest {
    @Test
    public void createServerTest(){
        Client c = new Client();
        c.createServer();
    }
}