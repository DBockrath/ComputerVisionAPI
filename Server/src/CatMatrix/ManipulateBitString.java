package CatMatrix;

import NeuralNetwork.BitString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManipulateBitString {

    private final int numOfPixels = 57600;

    // Compresses bits using hexadecimel digits to represent the number of the same bits in a given group
    public BitString compress(BitString bitString) {

        if (!bitString.isCompressed()) {

            Pattern pattern = Pattern.compile("0+|1+");
            Matcher matcher = pattern.matcher(toString());

            BitString bitsCopy = new BitString(0);
            int i = 0;

            while (matcher.find()) {

                if (matcher.group(0).length() < 16) {

                    if (matcher.group(0).startsWith("1")) {

                        bitsCopy.add(i++, '1');
                        bitsCopy.add(i++, getExtension(matcher.group(0).length()));

                    } else if (matcher.group(0).startsWith("0")) {

                        bitsCopy.add(i++, '0');
                        bitsCopy.add(i++, getExtension(matcher.group(0).length()));

                    }

                } else if (matcher.group(0).length() >= 16) {

                    if (matcher.group(0).startsWith("1")) {

                        bitsCopy.add(i++, '1');
                        bitsCopy.add(i++, getExtension(15));

                        int leftover = matcher.group(0).length() - 15;

                        while (leftover >= 16) {

                            bitsCopy.add(i++, '1');
                            bitsCopy.add(i++, getExtension(15));
                            leftover -= 15;

                        }

                        bitsCopy.add(i++, '1');
                        bitsCopy.add(i++, getExtension(leftover));

                    } else if (matcher.group(0).startsWith("0")) {

                        bitsCopy.add(i++, '0');
                        bitsCopy.add(i++, getExtension(15));

                        int leftover = matcher.group(0).length() - 15;

                        while (leftover >= 16) {

                            bitsCopy.add(i++, '0');
                            bitsCopy.add(i++, getExtension(15));
                            leftover -= 15;

                        }

                        bitsCopy.add(i++, '0');
                        bitsCopy.add(i++, getExtension(leftover));

                    }

                }

            }

            if (bitsCopy.size() >= bitString.size())
                return bitString;

            else
                return bitsCopy;

        }

        return bitString;

    }

    // Gets the hexadecimel digit based on the number of bits in a group
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

    // Decompresses bits so it is back to a standard bit string
    public BitString decompress(BitString bitString) {

        if (bitString.isCompressed()) {

           BitString bitsCopy = new BitString(0);

            for (int i = 0; i < bitString.size() - 1; i += 2) {

                int length = 0;

                switch (bitString.get(i + 1)) {

                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        length = Character.getNumericValue(bitString.get(i + 1));
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

                for (int j = 1; j <= length; j++)
                    bitsCopy.add(i, bitString.get(i));

            }

            return bitsCopy;

        }

        return bitString;

    }

    // Pre: Takes in a BitString to compare with bits
    // Post: Returns the percent deviation between bits and the given BitString in decompressed form
    public double calcPercentDeviation(BitString bitString1, BitString bitString2) {

        if (bitString1.isCompressed())
            bitString1 = decompress(bitString1);

        if (bitString2.isCompressed())
            bitString2 = decompress(bitString2);

        int numOfDeviations = 0;
        int size = bitString1.size();

        for (int i = 0; i < size; i++) {

            if (bitString1.get(i) != bitString2.get(i)) numOfDeviations += 1;

        }

        return (size - numOfDeviations) / size;

    }

    // Pre: Takes in an array of BitStrings
    // Post: Averages bits with given BitStrings and returns a new BitString that contains the average values
    public BitString calcAverageBitString(BitString[] bitStrings) {

        for (int i = 0; i < bitStrings.length; i++)
            if (bitStrings[i].isCompressed())
                bitStrings[i] = decompress(bitStrings[i]);

        int size = bitStrings[0].size();
        BitString averageBitString = new BitString(size);

        for (int bit = 0; bit < size; bit++) {

            int sum = 0;

            for (BitString bitString : bitStrings)
                sum += Character.getNumericValue(bitString.get(bit));

            averageBitString.set(bit, (char)(Math.round(sum / bitStrings.length) + '0'));

        }

        return averageBitString;

    }

    // Returns what the BitString would look like as a 240x240 pixel image
    public int[][] getImage(BitString bitString) {

        if (bitString.isCompressed())
            bitString = decompress(bitString);

        int[][] image = new int[(int)Math.sqrt(bitString.size())][(int)Math.sqrt(bitString.size())];
        int i = 0;

        for (int row = 0; row < image.length; row++)
            for (int column = 0; column < image[0].length; column++)
                image[row][column] = Character.getNumericValue(bitString.get(i++));

        return image;

    }

    // Returns the bipolar representation of bits
    public double[] toBipolarArray(BitString bitString) {

        if (bitString.isCompressed())
            bitString = decompress(bitString);

        double[] bipolarArray = new double[bitString.size()];

        for (int i = 0; i < bitString.size(); i++)
            bipolarArray[i] = (Character.getNumericValue(bitString.get(i)) * 2) - 1;

        return bipolarArray;

    }

    // Returns bits as a double array
    public double[] toDoubleArray(BitString bitString) {

        if (bitString.isCompressed())
            bitString = decompress(bitString);

        double[] returnArray = new double[bitString.size()];

        for (int i = 0; i < bitString.size(); i++)
            returnArray[i] = Character.getNumericValue(bitString.toString().charAt(i));

        return returnArray;

    }

    // Returns bits in its compressed state
    public String toCompressedString(BitString bitString) {

        if (!bitString.isCompressed())
            bitString = compress(bitString);

        StringBuilder builder = new StringBuilder(bitString.size());

        for (Character aBitString : bitString)
            builder.append(aBitString);

        return builder.toString();

    }

    // Returns bits in its decompressed state
    public String toDecompressedString(BitString bitString) {

        if (bitString.isCompressed())
            bitString = decompress(bitString);

        StringBuilder builder = new StringBuilder(bitString.size());

        for (Character aBitString : bitString)
            builder.append(aBitString);

        return builder.toString();

    }

}