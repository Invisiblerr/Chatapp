package ru.my.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;


public class ChatServer {

    public static void main(String[] args) {
        Set<ServerThread> users = new HashSet<>();
        try (ServerSocket serverSocket = new ServerSocket(8000)){
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread userThread = new ServerThread(socket);
                System.out.println("user connected");
                userThread.start();
                users.add(userThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



