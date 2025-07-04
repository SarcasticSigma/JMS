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
        new SyncServer();
        assertEquals(200, httpRequest(new URL("http://localhost:8195/jms/compare"), "POST"));
        assertEquals(200, httpRequest(new URL("http://localhost:8195/jms/compare"), "GET"));
        assertEquals(200, httpRequest(new URL("http://localhost:8195/jms/data"), "GET"));
        assertEquals(200, httpRequest(new URL("http://localhost:8195/jms/data"), "POST"));
    }


    public int httpRequest(URL url, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        return connection.getResponseCode();
    }

}