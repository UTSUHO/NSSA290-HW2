package Servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.net.SocketException;

public class UDPServer {
 
    private DatagramSocket udpSocket;
    private int port;
 
    public UDPServer(int port) throws SocketException, IOException {
      
          this.port = port;
          this.udpSocket = new DatagramSocket(this.port); 
    }
    
    public void listen() throws Exception
    {
     
                System.out.println("Client is connecting");
                ServerThread st = new ServerThread(this.udpSocket);
                st.start();
    }
    
    public static void main(String[] args) throws Exception {
        UDPServer client = new UDPServer(17689);
        client.listen();
    }
    
    class ServerThread extends Thread
    {   
          private DatagramSocket udps;
          private int udpport;
       
        public ServerThread(DatagramSocket clientSocket)
        {
            this.udps = clientSocket;
           
            try {
               
                System.out.println("Client is connected");

            } catch (Exception e) {
                //TODO: handle exception
            }
        }
//send it to the github
        public void run()
        {
            try 
            {
                InetAddress net = InetAddress.getLocalHost();
               java.text.SimpleDateFormat localSimpleDateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
               Date localDate = new Date();
               String timeStampString = localSimpleDateFormat.format(localDate);
               
                System.out.println("-- Running Server at " + InetAddress.getLocalHost() + "--");
               String msg="";
        //this shouldn't ended
        while (true) {
            //just to test it
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            
            // blocks until a packet is received
            udpSocket.receive(packet);
            msg = new String(packet.getData()).trim();
            String newMsg = net + " "+ timeStampString+" "+ msg; 
            byte[] buf2 = newMsg.getBytes();
            
            packet = new DatagramPacket(buf2, buf2.length);
            //there is no send so your side is still waiting for it
            udpSocket.send(packet);
            
            System.out.println(
                "Message from " + packet.getAddress().getHostAddress() + ": " + msg);

      }
                    
         
            }catch(Exception e){}
        }
    }
}
//
