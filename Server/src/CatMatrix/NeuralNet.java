package CatMatrix;

import NeuralNetwork.BitString;
import NeuralNetwork.NeuralNetwork;

public class NeuralNet extends ListNodeMatrix{

    NeuralNetwork checkDev;
    NeuralNetwork catsNet;
    NeuralNetwork subNet;

    private double perDev = 50;
    private double numCat = 100; //should be moved to LNM

    private void check()
    { //goes through the entire database and move objects to fit a the current dev
        perDev = Math.pow(67.05910855, numCat);

    }

    public NeuralNet()
    {
        super.addMain("dan");
        super.printAll();
        System.out.println("yes");
    }

    //goals
    //input images, it adds an image to LNM and trains the nueral net with this image

    public void inputImages(BitString in, String name)
    {

    }

    public String getName(BitString in)
    {
    //dan do your stuff!!!


        return null;
    }



}
