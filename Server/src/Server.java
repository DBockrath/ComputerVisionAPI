import ListNodeMatrix.ListNodeMatrix;
import NeuralNetwork.NeuralNetwork;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server extends Thread {

    private int neurons = 0;

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);

    }

    public void run() {

        while (true) {

            try {

                Socket server = serverSocket.accept();

                System.out.println("Connected to Client");
                System.out.println("");

                DataInputStream in = new DataInputStream(server.getInputStream());
                useNeuralNet(in.readUTF());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting");

                server.close();

            } catch (SocketTimeoutException s) {

                System.out.println("Socket timed out");
                System.out.println("Re-opening socket...");
                System.out.println("");

            } catch (IOException e) {

                e.printStackTrace();
                break;

            }

        }

    }

    private double[] convertInput(String input) {

        double[] returnData = new double[input.length()];
        System.out.println("Input: " + input);

        for (int i = 0; i < input.length(); i++) {

            returnData[i] = Double.parseDouble(String.valueOf(input.charAt(i)));

        }

        return returnData;

    }

    private void useNeuralNet(String in) {

        String[] data = in.split(",");

        if (neurons == 0) neurons = Integer.parseInt(data[0]);

        String command = data[1];

        System.out.println("Neurons: " + neurons);
        System.out.println("Command: " + command);

        double[] input = convertInput(data[2]);

        NeuralNetwork neuralNetwork = new NeuralNetwork(neurons);

        try {

            switch (command) {

                case "Train":
                    neuralNetwork.train(input);
                    break;

                case "Run":
                    neuralNetwork.run(input);
                    break;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    public static void main(String[] args) {

        ListNodeMatrix LNM = new ListNodeMatrix();
        boolean testing = true;

        if (testing) {
            LNM.addMain(null, "dan");
            LNM.addMain(null, "Ronnie");
            LNM.addSub("Hello dan", "Hello Dan", 0);
            LNM.addSub("Hello dan 2", "Hello Dan 2", 0);
            LNM.addSub("Hello dan 3", "Hello dan 3", 0);
            LNM.addSub("Hello rannie", "Hello Rannie", 1);
            LNM.addSub("Hello ronnie 2", "Hello Ronnie 2", 1);
            LNM.addSub("Hello ronnie 3", "Hello Ronnie 3", 1);

            System.out.println(LNM.getSubObject(1, 2));
            throw new NoSuchElementException("Testing finished, stopping code!!");
        }
        try {

            Scanner scanner = new Scanner(new File("C:\\Users\\Public\\Documents\\ServerData.txt"));

            String rName = scanner.nextLine();
            String dName = scanner.nextLine();
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