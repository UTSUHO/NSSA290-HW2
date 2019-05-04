package Servers;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.io.*;
import java.io.DataOutputStream;

public class TCPServer{


    public void execute(int port)
    {
        try {
            ServerSocket ss = new ServerSocket(port);
            InetAddress net =  InetAddress.getLocalHost();

            String host = net.getHostAddress();
            
            System.out.println(host);

            while(true)
            {
            
                Socket s = ss.accept();
                System.out.println();
               ServerThread st = new ServerThread(s);
                st.start();
            }

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    class ServerThread extends Thread
    {   
        Socket s = null;
        BufferedReader br = null;
        PrintWriter pw = null;
       
            java.text.SimpleDateFormat localSimpleDateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date localDate = new Date();
            String timeStampString = localSimpleDateFormat.format(localDate);
            
           
        
            
            
        public ServerThread(Socket clientSocket)
        {
            this.s = clientSocket;
            try {
               
                System.out.println("Client is connected at "+ timeStampString);

            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        public void run()
        {
            try 
            {
               InetAddress net =  InetAddress.getLocalHost();
               String host = net.getHostAddress();
               DataOutputStream socketOut = new java.io.DataOutputStream(s.getOutputStream());
               DataInputStream socketIn = new DataInputStream(new java.io.BufferedInputStream(s.getInputStream()));
               BufferedReader input = new BufferedReader(new java.io.InputStreamReader(System.in));

               
              
                    String str ="";
                    
                   
                    while(str != "end")
                    {
                      str = socketIn.readUTF();
                    
                     System.out.println(str);
                    
                    
                    socketOut.writeUTF(host +" "+timeStampString+" "+str);
                    socketOut.flush();                   
                    System.out.println(str);

                    }
                    
            }catch(Exception e){}
        }
    }

}
