package CatMatrix;

import NeuralNetwork.BitString;
import NeuralNetwork.Matrix;

public class NeuralNet extends ListNodeMatrix {

    /*
    TODO: Check over new methods to make sure they make sense
    TODO: Make body of inputImages(), getName(), and setCategories()
     */

    private Matrix categoryWeightMatrix;
    private final int numOfNeurons = 57600;
    private double minPercentDeviation = 50;

    public NeuralNet() {

        super.addMain("dan");
        super.printAll();
        System.out.println("yes");

    }

    // Goals
    // inputImages adds an image to LNM and trains the neural net with this image
    // decideCategory will calc the deviation percentage between a cat and an input
    // Each category will be and average of the sub objects. Use calcAverageBitString() to do this

    private void trainCategoryWeightMatrix() throws Exception {

        categoryWeightMatrix = new Matrix(numOfNeurons, numOfNeurons);

        for (int cat = 0; cat < numCats(); cat++) {

            BitString input = (BitString) super.getMainValue(cat);
            double[] bipolarInput = input.getBipolarArray();
            Matrix bipolarMatrix = Matrix.toRowMatrix(bipolarInput);
            Matrix transposeBipolarMatrix = bipolarMatrix.transpose();
            Matrix multiplyMatrix = transposeBipolarMatrix.multiply(bipolarMatrix);
            Matrix subtractMatrix = multiplyMatrix.subtract(Matrix.identity(categoryWeightMatrix.getData().length));
            categoryWeightMatrix = categoryWeightMatrix.add(subtractMatrix);

        }

    }

    private BitString runCategoryNetwork(BitString input) {

        BitString output = new BitString(input.Length());
        Matrix bipolarMatrix = Matrix.toRowMatrix(input.getBipolarArray());

        for (int column = 0; column < input.Length(); column++) {

            try {

                Matrix columnMatrix = categoryWeightMatrix.getColumnMatrix(column);
                double dotProductResult = bipolarMatrix.dotProduct(columnMatrix);

                if (dotProductResult > 0) {

                    output.set(column, '1');

                } else {

                    output.set(column, '0');

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return output;

    }

    private BitString runSubNetwork(BitString input, int category) throws Exception {

        Matrix subWeightMatrix = new Matrix(numOfNeurons, numOfNeurons);

        for (int pos = 0; pos < super.numSubNodes(category); pos++) {

            BitString subObject = (BitString) super.getSubObject(category, pos);
            double[] bipolarInput = subObject.getBipolarArray();
            Matrix bipolarMatrix = Matrix.toRowMatrix(bipolarInput);
            Matrix transposeBipolarMatrix = bipolarMatrix.transpose();
            Matrix multiplyMatrix = transposeBipolarMatrix.multiply(bipolarMatrix);
            Matrix subtractMatrix = multiplyMatrix.subtract(Matrix.identity(subWeightMatrix.getData().length));
            subWeightMatrix = subWeightMatrix.add(subtractMatrix);

        }

        BitString output = new BitString(input.Length());
        Matrix bipolarMatrix = Matrix.toRowMatrix(input.getBipolarArray());

        for (int column = 0; column < input.Length(); column++) {

            try {

                Matrix columnMatrix = subWeightMatrix.getColumnMatrix(column);
                double dotProductResult = bipolarMatrix.dotProduct(columnMatrix);

                if (dotProductResult > 0) {

                    output.set(column, '1');

                } else {

                    output.set(column, '0');

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return output;

    }

    private String decideCategory(BitString input, int numOfNodes) {

        BitString output = runCategoryNetwork(input);

        minPercentDeviation = Math.pow(67.05910855, numOfNodes);
        String category = null;
        double bestDeviation = .5;

        for (int cat = 0; cat < numOfNodes; cat++) {

            double deviation = output.calcPercentDeviation((BitString) super.getMainValue(cat));

            if (deviation > bestDeviation) {

                bestDeviation = deviation;
                category = super.getMainName(cat);

            }

        }

        return category;

    }

    private void setCategories() {

        // Sets the categories with the average BitString of each sub object

    }

    public void inputImages(BitString in, String name) {

        // Creates new weight matrices that have as many neurons as bits in BitString in


    }

    public String getName(BitString in) {

        //dan do your stuff!!!
        return null;

    }

}
