package Servers;

import java.io.PrintStream;
import java.net.InetAddress;
import java.util.Date;
import java.util.Scanner;

public class Main
{
    public Main() {}

    public static void main(String[] paramArrayOfString) throws Exception
    {
    
       InetAddress net =  InetAddress.getLocalHost();

       String host = net.getHostAddress();
       String hostName = net.getHostName();
    
        Scanner localScanner = new Scanner(System.in);
        System.out.print("Enter TCP or UDP: ");
        String protocolString = localScanner.next().toLowerCase();

        System.out.print("Enter Port: ");
        int i = localScanner.nextInt();


        java.text.SimpleDateFormat localSimpleDateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date localDate = new Date();
        String timeStampString = localSimpleDateFormat.format(localDate);

        System.out.println("Starting Up At Host: " +hostName+ " " + host + " using " + protocolString+" Server " + " on Port " + i + " at " + timeStampString);

//      initialization
        if (protocolString.equals("tcp")) {
            TCPServer tcps = new TCPServer();
            tcps.execute(i);
        } else if (protocolString.equals("udp")) {
            UDPServer udps = new UDPServer(i);
            
        } else {
            System.err.println("Unknown protocol " + protocolString);
            System.exit(-1);
        }
        localScanner.close();
    }
}
