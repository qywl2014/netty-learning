package com.wulang.oio_learning;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OIO_Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6666);
        while(true){
            final Socket clientSocket = serverSocket.accept();
            System.out.println("accept connect from"+clientSocket);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OutputStream outputStream=clientSocket.getOutputStream();
                        outputStream.write("hi".getBytes());
                        outputStream.flush();
                    }catch (Exception e){

                    }finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}

