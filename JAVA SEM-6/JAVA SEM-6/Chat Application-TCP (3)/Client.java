import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
            
            String msg = "";
            while (!msg.equalsIgnoreCase("goodbye")) {
                System.out.print("Enter message to send to server: ");
                String msg1 = sc.nextLine();
                dos.writeUTF(msg1);
                
                if (!msg1.equalsIgnoreCase("goodbye")) {
                    msg = dis.readUTF();
                    System.out.println("Message received from server: " + msg);
                } else {
                    msg = "goodbye";
                }
            }
            
            dos.close();
            dis.close();
            socket.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}