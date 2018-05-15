package CatMatrix;

import NeuralNetwork.BitString;
import NeuralNetwork.NeuralNetwork;

public class NeuralNet extends ListNodeMatrix{

    NeuralNetwork checkDeviationNetwork;
    NeuralNetwork categoriesNetwork;
    NeuralNetwork subNetwork;

    private double minPercentDeviation = 50;
    private double numberOfCategories = 100; // Should be moved to LNM

    public NeuralNet() {

        super.addMain("dan");
        super.printAll();
        System.out.println("yes");

    }

    // Goals
    // inputImages adds an image to LNM and trains the neural net with this image
    // decideCategory will calc the deviation percentage between a cat and an input

    private String decideCategory(BitString in, int cats) {

        // Goes through the entire database and move objects to fit a the current dev
        minPercentDeviation = Math.pow(67.05910855, cats);
        String category = null;

        for (int cat = 1; cat <= super.numNodes(); cat++) {

//            in.calcPercentDeviation(super.get);

        }

        return category;

    }

    public void inputImages(BitString in, String name) {

        // Creates new neural nets that have as many neurons as bits in BitString in
        categoriesNetwork = new NeuralNetwork(in.Length());
        subNetwork = new NeuralNetwork(in.Length());

    }

    public String getName(BitString in) {

        //dan do your stuff!!!
        return null;

    }

}
