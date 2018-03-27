import NeuralNetwork.NeuralNetwork;

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

                Socket server = serverSocket.accept();
                DataInputStream in = new DataInputStream(server.getInputStream());

                try {

                    Scanner scanner = new Scanner(in.readUTF());

                    int neurons = Integer.parseInt(scanner.nextLine());
                    String command = scanner.nextLine();
                    double[] input = convertInput(scanner.nextLine());

                    NeuralNetwork neuralNetwork = new NeuralNetwork(neurons);

                    switch (command) {

                        case "Train":
                            neuralNetwork.train(input);

                        case "Run":


                    }

                } catch (Exception err) {

                    System.out.println("Error reading input");

                }

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting " + "\nGoodbye!");

                server.close();

            } catch (SocketTimeoutException s) {

            } catch (IOException e) {

                e.printStackTrace();
                break;

            }

        }

    }

    private double[] convertInput(String input) {

        double[] returnData = new double[input.length()];

        for (int i = 0; i < input.length(); i++) {

            returnData[i] = Double.parseDouble(String.valueOf(input.charAt(i)));

        }

        return returnData;

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