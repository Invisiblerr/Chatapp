package ru.my.Server;

import java.io.*;
import java.net.Socket;


public class ServerThread extends Thread {
    private final Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String message;
    //public String recieved;


    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String recieved = receiveMessage();
                System.out.println(recieved);
                sendMessage(recieved);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void sendMessage(String message) {
        writer.println(message);
    }
}
