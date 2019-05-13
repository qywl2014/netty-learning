package com.wulang.oio_learning;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class OIO_Util {
    public static void chat(final InputStream inputStream, final OutputStream outputStream){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        byte[] response=new byte[1024];
                        inputStream.read(response);
                        String receivedMessage=new String(response);
                        System.out.println("["+receivedMessage+"]");
                        if("close".equals(receivedMessage)){
                            System.out.println("close .....");
                            inputStream.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner=new Scanner(System.in);
                while (scanner.hasNext()){
                    try{
                        String message=scanner.nextLine();
                        outputStream.write(message.getBytes());
                        outputStream.flush();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
