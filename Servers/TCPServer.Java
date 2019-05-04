package Servers;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.io.*;

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
                
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

               
                    String msg =(String)ois.readObject();
                    System.out.println("Server - MSG received = " + msg);
                 
                    msgList.add(msg);
                    //clientList.add(pw);
                   
                    oos.writeObject(msgList);
                    oos.flush();
                    for (String msgs : msgList)
                    {
                        oos.writeObject(msg);
                    }
                    oos.flush();

                    
         
            }catch(Exception e){}
        }
    }

}
