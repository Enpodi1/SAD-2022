import java.io.IOException;
import java.net.ServerSocket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class MyServerSocket {
    public ServerSocket serverSocket;

    public MyServerSocket(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MySocket accept() {
        try {
            MySocket socket = new MySocket(this.serverSocket.accept());
            return  socket;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}