package ru.my.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {


    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 8000);
            System.out.println("Connected to serverchat");


            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            Scanner scan = new Scanner(System.in);


            while (true) {

                String message = scan.nextLine();
                writer.println(message);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String chatlog = reader.readLine();
                System.out.println(chatlog);

            }
        }

        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
