package com.wulang.oio_learning;

import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class OIO_Client {
    public static void main(String[] args) throws Exception {
        Socket socket=new Socket("127.0.0.1",6666);

        System.out.println( socket.getLocalPort());

        InputStream inputStream=socket.getInputStream();
        Scanner scanner=new Scanner(inputStream);
        while (scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }
    }
}

