import java.io.*;
import java.net.*;

public class Server extends Thread{
    DataInputStream din;
    Socket s;
    Server( Socket s){
        this.s=s;
    }
    public void run(){
        try{
            din = new DataInputStream(s.getInputStream());
            String msg = din.readUTF();
            System.out.println("Message From Client : " +msg);
            din.close();
            s.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server Started....");
            while (true) {
                Socket s = ss.accept();
                System.out.println("Client connected...");
                new Server(s).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
