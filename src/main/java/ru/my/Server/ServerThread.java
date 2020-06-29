package ru.my.Server;

import java.io.*;
import java.net.Socket;

import java.util.Set;


public class ServerThread extends Thread {
    private final Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String message;
    private Set<ServerThread>user;


    public ServerThread(Socket socket, Set <ServerThread> set) {
        this.socket = socket;
        this.user = set;
    }

    public void run() {

        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            /*String mes = "Server message";
            sendMessage(mes,user);*/
            while (true) {
                String recieved = receiveMessage();
                System.out.println(recieved);
                sendMessage(recieved, user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void sendMessage(String message, Set<ServerThread> set) {
        for (ServerThread user : set) {
            writer.println(message);
        }
    }
}
