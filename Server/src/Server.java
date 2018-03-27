import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Server extends Thread {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);

    }

    public void run() {

        while (true) {

            try {

                System.out.println("Waiting for Client on port " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
                server.close();

            } catch (SocketTimeoutException s) {

                System.out.println("Socket timed out!");
                break;

            } catch (IOException e) {

                e.printStackTrace();
                break;

            }

        }

    }

    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(new File("C:\\Users\\diboc\\Documents\\IntelliJ Workspace\\ComputerVisionAPI\\Server\\ClientData"));
//            Scanner scanner = new Scanner(new File(""));


            int port = Integer.parseInt(scanner.nextLine());

            try {

                Thread t = new Server(port);
                t.start();

            } catch (IOException e) {

                e.printStackTrace();

            }

        } catch (IOException er) {

            System.out.println("Error reading from file");

        }

    }

}