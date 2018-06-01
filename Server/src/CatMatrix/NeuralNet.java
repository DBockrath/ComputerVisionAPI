package CatMatrix;

import NeuralNetwork.BitString;
import NeuralNetwork.Matrix;

import java.util.ArrayList;
import java.util.List;

public class NeuralNet extends ListNodeMatrix {

    /*
    TODO: Check over new methods to make sure they make sense
    TODO: Make body of inputImages(), getName(), and setCategories()
     */

    private Matrix categoryWeightMatrix;
    private ManipulateBitString manipulateBitString = new ManipulateBitString();
    private final int numOfNeurons = 1000;
    private double minPercentDeviation = .50;

    // Goals
    // inputImages adds an image to LNM and trains the neural net with this image
    // decideCategory will calc the deviation percentage between a cat and an input
    // Each category will be and average of the sub objects. Use calcAverageBitString() to do this

    public void trainCategoryWeightMatrix() throws Exception {

        categoryWeightMatrix = new Matrix(numOfNeurons, numOfNeurons);

        ListNode cur = super.getLastNode().getNext();

        while(cur != super.getLastNode()) {

            BitString input = new BitString(cur.getName());
            double[] bipolarInput = manipulateBitString.toBipolarArray(input);
            Matrix bipolarMatrix = Matrix.toRowMatrix(bipolarInput);
            Matrix transposeBipolarMatrix = bipolarMatrix.transpose();
            Matrix multiplyMatrix = transposeBipolarMatrix.multiply(bipolarMatrix);
            Matrix subtractMatrix = multiplyMatrix.subtract(Matrix.identity(categoryWeightMatrix.getData().length));
            categoryWeightMatrix = categoryWeightMatrix.add(subtractMatrix);

            cur = cur.getNext();

        }

        // For last node case
        BitString input = new BitString(super.getLastNode().getName());
        double[] bipolarInput = manipulateBitString.toBipolarArray(input);
        Matrix bipolarMatrix = Matrix.toRowMatrix(bipolarInput);
        Matrix transposeBipolarMatrix = bipolarMatrix.transpose();
        Matrix multiplyMatrix = transposeBipolarMatrix.multiply(bipolarMatrix);
        Matrix subtractMatrix = multiplyMatrix.subtract(Matrix.identity(categoryWeightMatrix.getData().length));
        categoryWeightMatrix = categoryWeightMatrix.add(subtractMatrix);

    }

    public ListNode runCategoryNetwork(BitString input) {

        BitString output = new BitString(input.size());
        Matrix bipolarMatrix = Matrix.toRowMatrix(manipulateBitString.toBipolarArray(input));

        for (int column = 0; column < input.size(); column++) {

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

        ListNode lastNode = super.getLastNode();
        ListNode cur = lastNode.getNext();

        while(cur != lastNode){
            if(cur.getName().equals(output.toString()))
            {
                return cur;
            }
            cur = cur.getNext();
        }

        if(lastNode.getName().equals(output.toString()))
        {
            return lastNode;
        }

        System.out.println("Cat found");

        return null;

    }

    private ListNode runSubNetwork(BitString input, ListNode cat) throws Exception {

        Matrix subWeightMatrix = new Matrix(numOfNeurons, numOfNeurons);

        ListNode cur = (ListNode) cat.getValue();
        cur  = cur.getNext();
        ListNode lastNode = (ListNode) cat.getValue();

        ArrayList<ListNode> temp = new ArrayList<>();

        while(cur != lastNode) {

            BitString subObject = (BitString) super.getSubObject(cat.getName(), cur.getName());
            double[] bipolarInput = manipulateBitString.toBipolarArray(subObject);
            Matrix bipolarMatrix = Matrix.toRowMatrix(bipolarInput);
            Matrix transposeBipolarMatrix = bipolarMatrix.transpose();
            Matrix multiplyMatrix = transposeBipolarMatrix.multiply(bipolarMatrix);
            Matrix subtractMatrix = multiplyMatrix.subtract(Matrix.identity(subWeightMatrix.getData().length));
            subWeightMatrix = subWeightMatrix.add(subtractMatrix);

            temp.add(cur);
            cur = cur.getNext();

        }

        // For last node case
        BitString subObject = (BitString) super.getSubObject(cat.getName(), lastNode.getName());
        double[] bipolarInput = manipulateBitString.toBipolarArray(subObject);
        Matrix bipolarMatrix = Matrix.toRowMatrix(bipolarInput);
        Matrix transposeBipolarMatrix = bipolarMatrix.transpose();
        Matrix multiplyMatrix = transposeBipolarMatrix.multiply(bipolarMatrix);
        Matrix subtractMatrix = multiplyMatrix.subtract(Matrix.identity(subWeightMatrix.getData().length));
        subWeightMatrix = subWeightMatrix.add(subtractMatrix);
        temp.add(lastNode);


        BitString output = new BitString(input.size());
        Matrix inputBipolarMatrix = Matrix.toRowMatrix(manipulateBitString.toBipolarArray(input));

        for (int column = 0; column < input.size(); column++) {

            try {

                Matrix columnMatrix = subWeightMatrix.getColumnMatrix(column);
                double dotProductResult = inputBipolarMatrix.dotProduct(columnMatrix);

                if (dotProductResult > 0) {

                    output.set(column, '1');

                } else {

                    output.set(column, '0');

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        while(!temp.isEmpty())
        {
            if(temp.get(temp.size()- 1).getValue().toString().equals(output))
            {
                return temp.get(temp.size()-1);
            }
            temp.remove(temp.size()-1);
        }

        //return output.toString();

        return null;
    }

    private ListNode decideCategory(BitString input) {

        BitString result = new BitString(runCategoryNetwork(input).getName());

        int numCats = 1;
        ListNode c = super.getLastNode().getNext();

        while (c != super.getLastNode())
            numCats++;

        minPercentDeviation = Math.pow(67.05910855, numCats) / 100;
        ListNode cur = super.getLastNode().getNext();
        ListNode category = cur;
        double bestDeviation = Math.round(minPercentDeviation);

        while (cur != super.getLastNode()) {

            double deviation = manipulateBitString.calcPercentDeviation(result, new BitString(cur.getName()));

            if (deviation > bestDeviation) {

                bestDeviation = deviation;
                category = (ListNode) cur.getValue();

            }

            cur = cur.getNext();

        }

        // For last node case
        double deviation = manipulateBitString.calcPercentDeviation(result, new BitString(cur.getName()));

        if (deviation > bestDeviation)
            category = (ListNode) cur.getValue();

        return category;

    }

    private void setCategories() {

        // Sets the categories with the average BitString of each sub object


    }

    public void inputImage(BitString in, String name) {

        ListNode cat = runCategoryNetwork(in);
        ListNode cur = super.getLastNode().getNext();
        int pos = 0;

        if (super.getLastNode() == cur)
            addSub(in.toString(), name, pos);

        else {

            while (cur != cat) {

                cur = cur.getNext();
                pos++;

            }

            addSub(in.toString(), name, pos);

        }

        System.out.println("Sub added");

//        if (super.getMainName(cat) == null) {
//
//            super.addMain(in.toString());
//            super.addSub(in, name, in.toString());
//
//        } else {
//            super.addSub(in, name, runCategoryNetwork(in).getName());
//        }

    }

    public void inputImages(BitString in, String name) {

        // Creates new weight matrices that have as many neurons as bits in BitString in


    }

    public void inputImages(int[][] in, String name) {

        // Creates new weight matrices that have as many neurons as bits in BitString in


    }

    public String getName(BitString in) {

        ListNode category = runCategoryNetwork(in);
        ListNode sub = null;

        try {

            sub = runSubNetwork(in, category);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return sub.getName();

    }

}