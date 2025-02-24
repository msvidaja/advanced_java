import java.io.*;
import java.net.*;
import java.util.*;

public class Client1 {
    public static void main(String[] args) {
        try {
           Socket s = new Socket("localhost", 5000);
           DataOutputStream dos = new DataOutputStream(s.getOutputStream());
           Scanner sc = new Scanner(System.in);
           System.out.print("Enter message to send to server: ");
           String msg = sc.nextLine();
           dos.writeUTF(msg);
           s.close();
           sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}