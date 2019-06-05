package com.wulang.http.jdk;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class JdkHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(7777),0);
        httpServer.createContext("/test", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                httpExchange.sendResponseHeaders(200,0);
                OutputStream outputStream=httpExchange.getResponseBody();
                String response="hello jdk http";
                outputStream.write(response.getBytes());
                outputStream.close();
            }
        });
        httpServer.start();
    }
}
