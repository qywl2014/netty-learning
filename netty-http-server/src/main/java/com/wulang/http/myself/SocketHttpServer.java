package com.wulang.http.myself;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketHttpServer {
    private static int count=1;
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
            System.out.println(count++);
            new Thread(new SocketThread(socket)).start();
        }
    }
}
class SocketThread implements Runnable{
    private Socket socket;

    public SocketThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream=socket.getOutputStream();
            InputStream inputStream=socket.getInputStream();
            byte b[]=new byte[1024];
            inputStream.read(b);
            System.out.println(new String(b));
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
//            PrintWriter printWriter=new PrintWriter(outputStream,true);
            String html="<!DOCTYPE html><html><head><title>浪哥</title></head><body><h4>浪哥流弊</h4></body></html>";
            String response="HTTP/1.1 404 ok\n"+"content-type:text/html;charset=utf-8\n"+"\n"+html;
            bufferedWriter.write(response);
            bufferedWriter.flush();
            outputStream.close();
            inputStream.close();
//            printWriter.print(response);
//            printWriter.close();

        }catch (Exception e){

        }
    }
}
