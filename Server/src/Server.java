//import CatMatrix.ListNodeMatrix;
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

//    private int neurons = 0;
//
//    private ServerSocket serverSocket;
//
//    public Server(int port) throws IOException {
//
//        serverSocket = new ServerSocket(port);
//        serverSocket.setSoTimeout(10000);
//
//    }
//
//    public void run() {
//
//        while (true) {
//
//            try {
//
//                Socket server = serverSocket.accept();
//
//                System.out.println("Connected to Client");
//                System.out.println("");
//
//                DataInputStream in = new DataInputStream(server.getInputStream());
//                useNeuralNet(in.readUTF());
//
//                DataOutputStream out = new DataOutputStream(server.getOutputStream());
//                out.writeUTF("Thank you for connecting");
//
//                server.close();
//
//            } catch (SocketTimeoutException s) {
//
//                System.out.println("Socket timed out");
//                System.out.println("Re-opening socket...");
//                System.out.println("");
//
//            } catch (IOException e) {
//
//                e.printStackTrace();
//                break;
//
//            }
//
//        }
//
//    }
//
//    private double[] convertInput(String input) {
//
//        double[] returnData = new double[input.length()];
//        System.out.println("Input: " + input);
//
//        for (int i = 0; i < input.length(); i++) {
//
//            returnData[i] = Double.parseDouble(String.valueOf(input.charAt(i)));
//
//        }
//
//        return returnData;
//
//    }
//
//    private void useNeuralNet(String in) {
//
//        String[] data = in.split(",");
//
//        if (neurons == 0) neurons = Integer.parseInt(data[0]);
//
//        String command = data[1];
//
//        System.out.println("Neurons: " + neurons);
//        System.out.println("Command: " + command);
//
//        double[] input = convertInput(data[2]);
//
//        NeuralNetwork neuralNetwork = new NeuralNetwork(neurons);
//
//        try {
//
//            switch (command) {
//
//                case "Train":
//                    neuralNetwork.train(input);
//                    break;
//
//                case "Run":
//                    neuralNetwork.run(input);
//                    break;
//
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//    }
//
//
//
//    public static void main(String[] args) {
//
//
//
//    }

}