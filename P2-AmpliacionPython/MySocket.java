import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class MySocket {
    public Socket socket;
    public BufferedReader bufferedReader;
    public PrintWriter printWriter;
    private String host;


    public MySocket(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MySocket(Socket socket) {
        this.socket = socket;
        this.host = socket.getRemoteSocketAddress().toString();
        start();
    }

    public String getHost(){
        return host;
    }



    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printLine(String msg) {
        printWriter.println(msg);
    }

    public void start() {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            this.printWriter.close();
            this.bufferedReader.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}