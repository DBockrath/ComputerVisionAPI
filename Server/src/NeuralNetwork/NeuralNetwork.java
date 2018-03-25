package NeuralNetwork;

import java.util.stream.IntStream;

public class NeuralNetwork {

    private Matrix weightMatrix;

    public NeuralNetwork(int size) {

        weightMatrix = new Matrix(size, size);

    }

    public Matrix getWeightMatrix() {

        return weightMatrix;

    }

    public void train(double[] input) throws Exception {

        double[] bipolarInput = toBipolar(input);
        Matrix bipolarMatrix = Matrix.toRowMatrix(bipolarInput);
        Matrix transposeBipolarMatrix = bipolarMatrix.transpose();
        Matrix multiplyMatrix = transposeBipolarMatrix.multiply(bipolarMatrix);
        Matrix subtractMatrix = multiplyMatrix.subtract(Matrix.identity(weightMatrix.getData().length));
        weightMatrix = weightMatrix.add(subtractMatrix);

    }

    public double[] run(double[] input) {

        double[] bipolarInput  = toBipolar(input);
        double[] output = new double[input.length];
        Matrix bipolarMatrix = Matrix.toRowMatrix(bipolarInput);

        IntStream.range(0, input.length).forEach( column -> {

            try {

                Matrix columnMatrix = weightMatrix.getColumnMatrix(column);
                double dotProductResult = bipolarMatrix.dotProduct(columnMatrix);

                if (dotProductResult > 0) {

                    output[column] = 1.00;

                } else {

                    output[column] = 0;

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        });

        return output;

    }

    static double[] toBipolar(double[] pattern) {

        double[] bipolarPattern = new double[pattern.length];

        IntStream.range(0, pattern.length).forEach( row -> {

            bipolarPattern[row] = (pattern[row] * 2) - 1;

        });

        return bipolarPattern;

    }

//    static double[] fromBipolar(double[] bipolarPattern) {
//
//        double[] pattern = new double[bipolarPattern.length];
//
//        IntStream.range(0, bipolarPattern.length).forEach( row -> {
//
//            pattern[row] = (bipolarPattern[row] + 1) / 2;
//
//        });
//
//        return pattern;
//
//    }

}