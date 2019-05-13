package com.wulang.oio_learning;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OIO_Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        while(true){
            final Socket clientSocket = serverSocket.accept();
            System.out.println("accept connect from"+clientSocket);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        final OutputStream outputStream=clientSocket.getOutputStream();
                        final InputStream inputStream=clientSocket.getInputStream();
                        OIO_Util.chat(inputStream,outputStream);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
//                        try {
//                            clientSocket.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }).start();
        }
    }
}

