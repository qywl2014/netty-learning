package com.wulang.http.myself;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class SocketHttpClient {
    public static void main(String[] args) throws Exception {
        Socket socket=new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",555));
        OutputStream outputStream=socket.getOutputStream();
        InputStream inputStream=socket.getInputStream();
//        PrintWriter printWriter=new PrintWriter(outputStream,true);
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
        String html="<!DOCTYPE html><html><head><title>浪哥</title></head><body><h4>浪哥流弊</h4></body></html>";
        String response="GET http://wwww.baidu.coom:80/ HTTP/1.1\n"+"Host: www.baidu.com:80\n\n";
        bufferedWriter.write(response);
        bufferedWriter.flush();
//        printWriter.print(response);
//        printWriter.close();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String str;
        while((str=bufferedReader.readLine())!=null){
            System.out.println(str);
        }

//        Scanner scanner=new Scanner(inputStream);
//        while(scanner.hasNextLine()){
//            System.out.println(scanner.nextLine());
//        }
//        scanner.close();
        socket.close();
        System.out.println("close connection");
    }
}

