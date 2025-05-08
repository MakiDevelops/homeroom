package org.orgst;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Multiplayer {
    public static void server(String path){
        try {
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Server started\nWaiting for client to connect");
            Socket client = server.accept();
            System.out.println("Client connected: " + client.getInetAddress());
            File file = new File(path);
            byte[] buffer = new byte[4096];
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            FileInputStream in = new FileInputStream(file);
            out.writeUTF(file.getName());
            out.writeLong(file.length());
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
            out.close();
            client.close();
            System.out.println("Finished");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String client(String ip){
        Scanner scan = new Scanner(System.in);
        try {
            Socket server = new Socket(ip, 3000);
            DataInputStream in = new DataInputStream(server.getInputStream());
            String fileName = in.readUTF();
            long fileSize = in.readLong();
            byte[] buffer = new byte[4096];
            File file = new File("tmp/"+fileName);
            if (!new File("tmp").exists()){File tmp = new File("tmp"); tmp.mkdirs();}
            file.createNewFile();
            FileOutputStream out = new FileOutputStream("tmp/"+fileName);
            int bytesRead;
            long totalBytesRead = 0;
            while (totalBytesRead < fileSize && (bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
            return fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
