import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BitString {

    private ArrayList<Character> bits;

    public BitString(String text) {

        bits = new ArrayList<>(text.length());

        for (int i = 0; i < text.length(); i++) {

            bits.add(text.charAt(i));

        }

        if (!isCompressed()) compress();

    }

    private void compress() {

        Pattern pattern = Pattern.compile("0+|1+");
        Matcher matcher = pattern.matcher(convertToString());

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

    private void decompress() {

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
                    length = Character.getNumericValue(bits.get(i +1));
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

            for (int j = 1; j <= length; j++) {

                bitsCopy.add(bits.get(i));

            }

        }

        bits = bitsCopy;

    }

    private String convertToString() {

        StringBuilder builder = new StringBuilder(bits.size());

        for (Character ch: bits) {

            builder.append(ch);

        }

        return builder.toString();

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
                extension = (char)(length +'0');
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

    boolean isCompressed() {

        String text = convertToString();

        return (text.contains("2") || text.contains("3") || text.contains("4") || text.contains("5") || text.contains("6") || text.contains("7") || text.contains("8") || text.contains("9")
                || text.contains("A") || text.contains("B") || text.contains("C") || text.contains("D") || text.contains("E") || text.contains("F"));

    }

    public String toString() {

        if (isCompressed()) decompress();

        StringBuilder builder = new StringBuilder(bits.size());

        for (Character ch: bits) {

            builder.append(ch);

        }

        if (!isCompressed()) compress();

        return builder.toString();

    }

}