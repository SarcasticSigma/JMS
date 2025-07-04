package org.sigmaplex.jms.util;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Objects;


public class SyncServer {
    HttpServer server;

    SyncServer() {
        this(Config.port);
    }

    SyncServer(int port) {
        try {
            HttpServer s = HttpServer.create();
            s.bind(new InetSocketAddress("0.0.0.0", port), 0);
            s.start();
            s.createContext("/jms/compare", exchange -> {
                String method = exchange.getRequestMethod();
                if (Objects.equals(method, "GET")) {
                    getCompareHandler(exchange);
                } else if (Objects.equals(method, "POST")) {
                    postCompareHandler(exchange);
                } else {
                    exchange.sendResponseHeaders(404, 0);
                }
            });
            s.createContext("/jms/data", exchange -> {
                String method = exchange.getRequestMethod();
                if (Objects.equals(method, "GET")) {
                    getDataHandler(exchange);
                } else if (Objects.equals(method, "POST")) {
                    postDataHandler(exchange);
                } else {
                    exchange.sendResponseHeaders(404, 0);
                }
            });
            this.server = s;
        } catch (IOException e) {
            throw new IllegalStateException("Server failed to start", e);
        }
    }


    /*
        TODO: Implement the below pseudocode

        Files will be wrapped into zip files for sending.

        The client posts a list of their file hashes to the server, if there are any missing or inequal, the server responds with:

            {"type": "desync", "files": [{filename: '0,0.png', type:"map"}, {filename: 'r.0.0.mca', type="cache"}]}
            The client closes the connection.

            The client should then open a connection to the dataContext:GET, and download all of inequal files, the files in one request.

            For each file, the client shall then merge the map types, and for the cache types, use the larger of the two files.

            Once done, the client shall open a new request to the dataContext:POST and send all of their files to the
            server where the server will compare and merge/overwrite the map and cache files with the same above logic.

            Finally, the client shall once again trigger a compare call with their hashes, presumably,
            this request will find no differences and this logic stream can terminate.
    */

    public void postCompareHandler(HttpExchange exchange) throws IOException {

        OutputStream body = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, 0);
    }

    public void getCompareHandler(HttpExchange exchange) throws IOException {

        OutputStream body = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, 0);
    }

    public void getDataHandler(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, 0);
    }

    public void postDataHandler(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, 0);
    }


}
