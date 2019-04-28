import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class TCPClient
{
    private Socket socket = null;
    private java.io.DataOutputStream socketOut = null;
    private DataInputStream socketIn = null;
    private BufferedReader input = null;

    public TCPClient(String paramString, int paramInt)
    {
        int counter = 0;
        try
        {
            socket = new Socket(paramString, paramInt);
            socketOut = new java.io.DataOutputStream(socket.getOutputStream());
            socketIn = new DataInputStream(new java.io.BufferedInputStream(socket.getInputStream()));
            input = new BufferedReader(new java.io.InputStreamReader(System.in));
        }
        catch (java.net.ConnectException localConnectException)
        {
            System.out.println(localConnectException);
            return;
        }
        catch (java.net.UnknownHostException localUnknownHostException)
        {
            System.out.println(localUnknownHostException);
            return;
        }
        catch (IOException localIOException1)
        {
            System.out.println(localIOException1); return;
        }
        for (;;)
        {
            if (counter == 0) {
                try
                {
                    String str = input.readLine();
                    if (str.equals("end")) {
                        counter = 1;
                        try {
                        input.close();
                        socketOut.close();
                        socketIn.close();
                        socket.close();
                        } catch (IOException localIOException3) {
                            System.out.println(localIOException3);
                        }
                    }
                    socketOut.writeUTF(str);
                    str = socketIn.readUTF();
                    System.out.println(str);
                }
                catch (java.io.EOFException localEOFException)
                {
                    System.out.println("Disconnected");
                }
                catch (IOException localIOException2)
                {
                    System.out.println(localIOException2);
                }
            }
        }
    }
}