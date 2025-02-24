import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1234);
            Socket socket = server.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            
            String msg = "";
            while (!msg.equalsIgnoreCase("goodbye")) {
                msg = dis.readUTF();
                System.out.println("Message received from client: " + msg);
                
                if (!msg.equalsIgnoreCase("goodbye")) {
                    System.out.print("Enter message to send to client: ");
                    String msg1 = sc.nextLine();
                    dos.writeUTF(msg1);
                }
            }
            
            dis.close();
            dos.close();
            socket.close();
            server.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}