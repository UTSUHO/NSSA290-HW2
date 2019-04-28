import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient
{
    private BufferedReader input = null;
    private DatagramSocket socket;
    private InetAddress address;

    public UDPClient(String paramString, int paramInt)
    {
        int counter = 0;
        try
        {
            socket = new DatagramSocket();
            address = InetAddress.getByName(paramString);
            input = new BufferedReader(new java.io.InputStreamReader(System.in));
        }
        catch (java.net.UnknownHostException localUnknownHostException)
        {
            System.out.println(localUnknownHostException);
        }
        catch (IOException localIOException1)
        {
            System.out.println(localIOException1);
        }
        for (;;) {
            if (counter == 0) {
                try
                {
                    String str1 = input.readLine();
                    if (str1.equals("end")) {
                        counter = 1;
                        try {
                            input.close();
                            socket.close();
                        } catch (IOException localIOException3) {
                            System.out.println(localIOException3);
                        }
                    }
                    byte[] arrayOfByte = str1.getBytes();
                    DatagramPacket localDatagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length, address, paramInt);
                    socket.send(localDatagramPacket);
                    arrayOfByte = new byte['Ā'];
                    localDatagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length);
                    socket.receive(localDatagramPacket);
                    String str2 = new String(localDatagramPacket.getData(), 0, localDatagramPacket.getLength());
                    System.out.println(str2);
                }
                catch (java.io.EOFException localEOFException)
                {
                    System.out.println("EOF");
                }
                catch (IOException localIOException2)
                {
                    System.out.println(localIOException2);
                }
            }
        }
    }
}
