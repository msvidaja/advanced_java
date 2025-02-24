import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Reciver {
    public static void main(String[] args) {
        try{
            DatagramSocket ds = new DatagramSocket(4600); 
            Scanner sc = new Scanner(System.in);
            String msg = "";   
            do{ 
                byte [] buf = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, 1024);
                ds.receive(dp);
                String rmsg = new String(dp.getData(),0,dp.getLength());
                System.out.println("Message From Sender: "+rmsg);
                
                if(rmsg.equalsIgnoreCase("goodbye")){
                    break;
                }

                System.out.println("Type a message: ");
                msg = sc.nextLine();

                byte[] buff = msg.getBytes();
                InetAddress ip = InetAddress.getByName("localhost");
                dp = new DatagramPacket(buff, msg.length(),ip,dp.getPort());
                ds.send(dp);
              
            }while(!msg.equalsIgnoreCase("goodbye"));
            sc.close();
            ds.close();
        } catch (Exception e) {}
    }    
}
