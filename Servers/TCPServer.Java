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

    public static ArrayList<String> msgList = new ArrayList<String>();

    public static void main(String[] args) 
    {
        
        int port = 17689;
            new TCPServer().execute(port);
            
    }


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
                System.out.println("Client is connecting");
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
       
        public ServerThread(Socket clientSocket)
        {
            this.s = clientSocket;
            try {
               
                System.out.println("Client is connected");

            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        public void run()
        {
            try 
            {
                
                //ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                //ObjectInputStream ois = new DataInputStream(s.getInputStream());
                
               
                
             
            DataOutputStream socketOut = new java.io.DataOutputStream(s.getOutputStream());
            DataInputStream socketIn = new DataInputStream(new java.io.BufferedInputStream(s.getInputStream()));
            BufferedReader input = new BufferedReader(new java.io.InputStreamReader(System.in));

               
                    
                    String str = input.readLine();
                    System.out.println(str);    
                
                    str = socketIn.readUTF();
                    socketOut.writeUTF(str);
                    socketOut.flush();                   
                    System.out.println(str);
                    
                 
               

                    
         
            }catch(Exception e){}
        }
    }

}
