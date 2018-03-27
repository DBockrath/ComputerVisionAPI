import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        String rName = null;
        String dName = null;
        int port = 0;

        try {

            Scanner scanner = new Scanner(new File("C:\\Users\\diboc\\Documents\\IntelliJ Workspace\\ComputerVisionAPI\\Client\\ServerData"));
//            Scanner scanner = new Scanner(new File(""));


            rName = scanner.nextLine();
            dName = scanner.nextLine();
            port = Integer.parseInt(scanner.nextLine());


        } catch (IOException e) {

            System.out.println("Error reading from file");

        }

        try {

            String serverName = rName;

            Socket client = new Socket(serverName, port);

            System.out.println("Connected to Server 1");
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from Server 1");
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Server 1 says " + in.readUTF());
            client.close();

        } catch (IOException e) {

            try {

                String serverName = dName;

                Socket client = new Socket(serverName, port);

                System.out.println("Connected to Server 2");
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                out.writeUTF("Hello from Server 2");
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);

                System.out.println("Server 2 says " + in.readUTF());
                client.close();

            } catch (IOException er) {

                System.out.println("Could not connect to Server 1 or 2");

            }

        }

    }

}