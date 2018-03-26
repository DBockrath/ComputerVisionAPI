import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        String serverName = args[0];
        int port = 25565;

        try {

            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Server says " + in.readUTF());
            //establish connection



            client.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}