package NeuralNetwork;

import CatMatrix.ManipulateBitString;

public class NeuralNetwork {

    private Matrix weightMatrix;
    private ManipulateBitString manipulateBitString = new ManipulateBitString();

    public NeuralNetwork(int size) {

        weightMatrix = new Matrix(size, size);

    }

    public Matrix getWeightMatrix() {

        return weightMatrix;

    }

    public void train(BitString input) throws Exception {

        double[] bipolarInput = manipulateBitString.toBipolarArray(input);
        Matrix bipolarMatrix = Matrix.toRowMatrix(bipolarInput);
        Matrix transposeBipolarMatrix = bipolarMatrix.transpose();
        Matrix multiplyMatrix = transposeBipolarMatrix.multiply(bipolarMatrix);
        Matrix subtractMatrix = multiplyMatrix.subtract(Matrix.identity(weightMatrix.getData().length));
        weightMatrix = weightMatrix.add(subtractMatrix);

    }

    public BitString run(BitString input) {

        BitString output = new BitString(input.size());
        Matrix bipolarMatrix = Matrix.toRowMatrix(manipulateBitString.toBipolarArray(input));

        for (int column = 0; column < input.size(); column++) {

            try {

                Matrix columnMatrix = weightMatrix.getColumnMatrix(column);
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

}