package NeuralNetwork;

import java.util.ArrayList;

public class BitString extends ArrayList<Character> {

    /*
    An image input will always be a square that is either 480x480 pixels or 240x240 pixels. The app will auto set this
    A BitString is stored as an ArrayList of chars
     */

    private final int numOfPixels = 1000;

    // Constructs using a String
    public BitString(String text) {

        for (int i = 0; i < text.length(); i++)
            super.add(text.charAt(i));

        if (super.size() != numOfPixels) {

            ArrayList<Character> newBits = new ArrayList<>(numOfPixels);
            int[][] matrix = new int[(int)Math.sqrt(numOfPixels)][(int)Math.sqrt(numOfPixels)];

            for (int row = 0; row < matrix.length; row++) {

                int i = row * 478;

                for (int col = 0; col < matrix[0].length; col++) {

                    matrix[row][col] = Math.round((super.get(i + 239) + super.get(i++) + super.get(i + 239) + super.get(i++)) / 4);
                    newBits.add((char)(matrix[row][col] + '0'));

                }

            }

            int lastIndex = 0;

            for (int i = 0; i < newBits.size(); i++) {

                super.set(i, newBits.get(i));
                lastIndex = i;

            }

            for (int i = lastIndex + 1; i < super.size(); i++)
                super.remove(i);

        }

    }

    // Constructs using an image
    public BitString(int[][] image) {

        for (int[] anImage : image)
            for (int column = 0; column < image[0].length; column++)
                super.add((char) anImage[column]);

        if (super.size() != numOfPixels) {

            ArrayList<Character> newBits = new ArrayList<>(numOfPixels);
            int[][] matrix = new int[(int)Math.sqrt(numOfPixels)][(int)Math.sqrt(numOfPixels)];

            for (int row = 0; row < matrix.length; row++) {

                int i = row * 478;

                for (int col = 0; col < matrix[0].length; col++) {

                    matrix[row][col] = Math.round((super.get(i + 239) + super.get(i++) + super.get(i + 239) + super.get(i++)) / 4);
                    newBits.add((char)(matrix[row][col] + '0'));

                }

            }

            int lastIndex = 0;

            for (int i = 0; i < newBits.size(); i++) {

                super.set(i, newBits.get(i));
                lastIndex = i;

            }

            for (int i = lastIndex + 1; i < super.size(); i++)
                super.remove(i);

        }

    }

    // Constructs super with the given length and each bit equals 0
    public BitString(int length) {

        for (int i = 0; i < length; i++)
            super.add('E');

    }

    // Returns true if in compressed form
    public boolean isCompressed() {

        return (super.contains("2") || super.contains("3") || super.contains("4") || super.contains("5") || super.contains("6") || super.contains("7") || super.contains("8") || super.contains("9")
                || super.contains("A") || super.contains("B") || super.contains("C") || super.contains("D") || super.contains("E") || super.contains("F"));

    }

    // Removes all bits
    public void delete() {

         for (int i = 0; i < super.size(); i++)
             super.remove(i);

    }

    // Returns super as a string in its current state
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder(super.size());

        for (int i = 0; i < super.size(); i++)
            builder.append(super.get(i));

        return builder.toString();

    }

}