import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) {
        try{
            DatagramSocket ds = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            String msg = "";
            do{ 
                
                System.out.println("Type a message: ");
                msg = sc.nextLine();

                byte[] buff = msg.getBytes();
                InetAddress ip = InetAddress.getByName("localhost");
                DatagramPacket dp = new DatagramPacket(buff, msg.length(),ip,4600);
                ds.send(dp);

                byte [] buf = new byte[1024];
                dp = new DatagramPacket(buf, 1024);
                ds.receive(dp);
                String rmsg = new String(dp.getData(),0,dp.getLength());
                System.out.println("Message From Reciver: "+rmsg);  
                
            }while(!msg.equalsIgnoreCase("goodbye"));
            sc.close();
            ds.close();
        } catch (Exception e) {}
    }    
}
