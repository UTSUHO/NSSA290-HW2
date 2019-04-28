import java.io.PrintStream;
import java.net.InetAddress;
import java.util.Date;
import java.util.Scanner;

public class Main
{
    public Main() {}

    public static void main(String[] paramArrayOfString)
    {
        String IPAddressString = "";
        Scanner localScanner = new Scanner(System.in);
        System.out.print("Enter the name or IP address of the Server: ");
        String hostnameString = localScanner.next().toLowerCase();
        System.out.print("Enter TCP or UDP: ");
        String protocolString = localScanner.next().toLowerCase();

        System.out.print("Enter Port: ");
        int i = localScanner.nextInt();


        java.text.SimpleDateFormat localSimpleDateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date localDate = new Date();
        String timeStampString = localSimpleDateFormat.format(localDate);
        try
        {
            InetAddress localInetAddress = InetAddress.getByName(hostnameString);
            IPAddressString = localInetAddress.getHostAddress();
        }
        catch (java.net.UnknownHostException localUnknownHostException) {
            System.out.println("Can't resolve hostname " + localUnknownHostException);
            System.exit(-1);
        }

        System.out.println("Connecting to " + hostnameString + " with IP address " + IPAddressString + " using " + protocolString + " on Port " + i + " at " + timeStampString);

//      initialization
        if (protocolString.equals("tcp")) {
            TCPClient tcpc = new TCPClient(hostnameString, i);
        } else if (protocolString.equals("udp")) {
            UDPClient udpc = new UDPClient(hostnameString, i);
        } else {
            System.err.println("Unknown protocol " + protocolString);
            System.exit(-1);
        }
        localScanner.close();
    }
}