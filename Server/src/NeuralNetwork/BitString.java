package NeuralNetwork;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BitString {

    private ArrayList<Character> bits;
    private final int numOfPixels = 57600;

    public BitString(String text) {

        bits = new ArrayList<>(text.length());

        for (int i = 0; i < text.length(); i++) bits.add(text.charAt(i));

        decompress();
        if (bits.size() != numOfPixels) resize();
        compress();

    }

    public BitString(int[][] image) {

        bits = new ArrayList<>((int)Math.pow(image.length, 2));

        for (int[] anImage : image)
            for (int column = 0; column < image[0].length; column++)
                bits.add((char) anImage[column]);

        decompress();
        if (bits.size() != numOfPixels) resize();
        compress();

    }

    public BitString(int length) {

        bits = new ArrayList<>(length);

        for (int i = 0; i < bits.size(); i++)
            bits.set(i, '0');

    }

    private void resize() {

        decompress();

        ArrayList<Character> newBits = new ArrayList<>(numOfPixels);
        int[][] matrix = new int[(int)Math.sqrt(numOfPixels)][(int)Math.sqrt(numOfPixels)];

        for (int row = 0; row < matrix.length; row++) {

            int i = row * 478;

            for (int col = 0; col < matrix[0].length; col++) {

                matrix[row][col] = Math.round((bits.get(i + 239) + bits.get(i++) + bits.get(i + 239) + bits.get(i++)) / 4);
                newBits.add((char)(matrix[row][col] + '0'));

            }

        }

        bits = newBits;
        compress();

    }

    private void compress() {

        if (!isCompressed()) {

            Pattern pattern = Pattern.compile("0+|1+");
            Matcher matcher = pattern.matcher(getCurrentString());

            ArrayList<Character> bitsCopy = new ArrayList<>();

            while (matcher.find()) {

                if (matcher.group(0).length() < 16) {

                    if (matcher.group(0).startsWith("1")) {

                        bitsCopy.add('1');
                        bitsCopy.add(getExtension(matcher.group(0).length()));

                    } else if (matcher.group(0).startsWith("0")) {

                        bitsCopy.add('0');
                        bitsCopy.add(getExtension(matcher.group(0).length()));

                    }

                } else if (matcher.group(0).length() >= 16) {

                    if (matcher.group(0).startsWith("1")) {

                        bitsCopy.add('1');
                        bitsCopy.add(getExtension(15));

                        int leftover = matcher.group(0).length() - 15;

                        while (leftover >= 16) {

                            bitsCopy.add('1');
                            bitsCopy.add(getExtension(15));
                            leftover -= 15;

                        }

                        bitsCopy.add('1');
                        bitsCopy.add(getExtension(leftover));

                    } else if (matcher.group(0).startsWith("0")) {

                        bitsCopy.add('0');
                        bitsCopy.add(getExtension(15));

                        int leftover = matcher.group(0).length() - 15;

                        while (leftover >= 16) {

                            bitsCopy.add('0');
                            bitsCopy.add(getExtension(15));
                            leftover -= 15;

                        }

                        bitsCopy.add('0');
                        bitsCopy.add(getExtension(leftover));

                    }

                }

            }

            bits = bitsCopy;

        }

    }

    private char getExtension(int length) {

        char extension = '1';

        switch (length) {

            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                extension = (char)(length + '0');
                break;

            case 10:
                extension = 'A';
                break;

            case 11:
                extension = 'B';
                break;

            case 12:
                extension = 'C';
                break;

            case 13:
                extension = 'D';
                break;

            case 14:
                extension = 'E';
                break;

            case 15:
                extension = 'F';
                break;

        }

        return extension;

    }

    private void decompress() {

        if (isCompressed()) {

            ArrayList<Character> bitsCopy = new ArrayList<>();

            for (int i = 0; i < bits.size() - 1; i += 2) {

                int length = 0;

                switch (bits.get(i + 1)) {

                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        length = Character.getNumericValue(bits.get(i + 1));
                        break;

                    case 'A':
                        length = 10;
                        break;

                    case 'B':
                        length = 11;
                        break;

                    case 'C':
                        length = 12;
                        break;

                    case 'D':
                        length = 13;
                        break;

                    case 'E':
                        length = 14;
                        break;

                    case 'F':
                        length = 15;
                        break;

                }

                for (int j = 1; j <= length; j++) bitsCopy.add(bits.get(i));

            }

            bits = bitsCopy;

        }

    }

    public boolean isCompressed() {

        String text = getCurrentString();

        return (text.contains("2") || text.contains("3") || text.contains("4") || text.contains("5") || text.contains("6") || text.contains("7") || text.contains("8") || text.contains("9")
                || text.contains("A") || text.contains("B") || text.contains("C") || text.contains("D") || text.contains("E") || text.contains("F"));

    }

    public void deleteBits() {

        bits = null;

    }

    public double calcPercentDeviation(BitString other) {

        decompress();
        other.decompress();

        int numOfDeviations = 0;
        int size = bits.size();

        for (int i = 0; i < size; i++) {

            if (bits.get(1) != other.get(i)) numOfDeviations += 1;

        }

        compress();
        other.compress();
        return (size - numOfDeviations) / size;

    }

    public int Length() {

        decompress();

        int size = bits.size();

        compress();
        return size;

    }

    public char get(int pos) {

        decompress();

        char bit = bits.get(pos);

        compress();
        return bit;

    }

    public void set(int pos, char val) {

        decompress();
        bits.set(pos, val);
        compress();

    }

    public void set(int pos, int val) {

        decompress();
        bits.set(pos, (char)(val + '0'));
        compress();

    }

    public int[][] getImage() {

        decompress();

        int[][] image = new int[(int)Math.sqrt(bits.size())][(int)Math.sqrt(bits.size())];
        int i = 0;

        for (int row = 0; row < image.length; row++)
            for (int column = 0; column < image[0].length; column++)
                image[row][column] = Character.getNumericValue(bits.get(i++));

        compress();
        return image;

    }

    public double[] getBipolarArray() {

        decompress();
        double[] bipolarArray = new double[bits.size()];

        for (int row = 0; row < bits.size(); row++)
            bipolarArray[row] = (bits.get(row) * 2) - 1;

        compress();
        return bipolarArray;

    }

    public double[] getDoubleArray() {

        decompress();

        String string = toString();
        double[] returnArray = new double[string.length()];

        for (int i = 0; i < string.length(); i++)
            returnArray[i] = string.charAt(i);

        compress();
        return returnArray;

    }

    public String getCurrentString() {

        StringBuilder builder = new StringBuilder(bits.size());

        for (Character ch: bits)
            builder.append(ch);

        return builder.toString();

    }

    public String getCompressedString() {

        compress();

        StringBuilder builder = new StringBuilder(bits.size());

        for (Character ch: bits)
            builder.append(ch);

        return builder.toString();

    }

    public String getDecompressedString() {

        decompress();

        StringBuilder builder = new StringBuilder(bits.size());

        for (Character ch: bits)
            builder.append(ch);

        compress();
        return builder.toString();

    }

}