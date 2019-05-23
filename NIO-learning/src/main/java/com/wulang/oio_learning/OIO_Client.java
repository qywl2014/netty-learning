package com.wulang.oio_learning;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class OIO_Client {
    public static void main(String[] args) throws Exception {
        Socket socket=new Socket("127.0.0.1",1234);
        final InputStream inputStream=socket.getInputStream();
        final OutputStream outputStream=socket.getOutputStream();
        OIO_Util.chat(inputStream,outputStream);
    }



    private static void readByOthers(InputStream inputStream) throws Exception{
    }



    public static void readByScanner(InputStream inputStream){
        Scanner scanner=new Scanner(inputStream);
        System.out.println("1");
        while (scanner.hasNext()){
            System.out.println(2);
            System.out.println(scanner.nextLine());
            System.out.println(3);
        }
        scanner.close();
    }
}

