package org.sigmaplex.jms;

import org.junit.jupiter.api.Test;
import org.sigmaplex.jms.util.SyncServer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class SyncServerTest {

    @Test
    public void testServerStarts() throws IOException {
        int testPort = 8195;
        new SyncServer(testPort);
        assertEquals(200, httpRequest(new URL(String.format("http://localhost:%d/jms/compare", testPort)), "POST"));
        assertEquals(200, httpRequest(new URL(String.format("http://localhost:%d/jms/compare", testPort)), "GET"));
        assertEquals(200, httpRequest(new URL(String.format("http://localhost:%d/jms/data", testPort)), "GET"));
        assertEquals(200, httpRequest(new URL(String.format("http://localhost:%d/jms/data", testPort)), "POST"));
    }


    public int httpRequest(URL url, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        return connection.getResponseCode();
    }

}