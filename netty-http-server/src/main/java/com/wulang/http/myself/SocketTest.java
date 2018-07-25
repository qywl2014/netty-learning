package com.wulang.http.myself;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(555);
//        while(true){
//            Socket socket = serverSocket.accept();
//            OutputStream outputStream=socket.getOutputStream();
//            PrintStream printStream=new PrintStream(outputStream);
//            String html="123";
//            printStream.println("HTTP/1.1 200 ok");
//            printStream.println("content-type:text/html;charset=utf-8");
//            printStream.println();
//            printStream.println("<!DOCTYPE html><html><head><title>浪哥</title></head><body><h4>浪哥流弊</h4></body></html>");
//            printStream.close();
//            socket.close();
//        }
        while(true){
            Socket socket = serverSocket.accept();
            OutputStream outputStream=socket.getOutputStream();
            String html="<!DOCTYPE html><html><head><title>浪哥</title></head><body><h4>浪哥流弊</h4></body></html>";
            String response="HTTP/1.1 200 ok\r\n"+"content-type:text/html;charset=utf-8\r\n"+"\r\n"+"123";
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
            socket.close();
            System.out.println("hi");
        }
//        System.out.println("hi\\n1");
    }
}

