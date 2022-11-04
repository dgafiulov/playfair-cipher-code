import java.util.Objects;

public class Matrix {

    private final String key;
    String alphabet = "abcdefghiklmnopqrstuvwxyz";
    String originalAlphabet = "abcdefghijklmnopqrstuvwxyz";

    private String[] bigrams;
    private String[][] matrix = new String[][]{{"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""}};
    public Matrix(String key) {
        this.key = separator(key);
    }

    public void fill(String key) {
        alphabet = uniqueSymbolsInAlphabet(alphabet, key);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!Objects.equals(key, "")) {
                    matrix[i][j] = String.valueOf(key.charAt(0));
                    key = key.substring(1);
                } else {
                    matrix[i][j] = String.valueOf(alphabet.charAt(0));
                    alphabet = alphabet.substring(1);
                }
            }
        }
    }

    public String getKey() {
        return this.key;
    }

    public void printMatrix() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(Objects.equals(matrix[i][j], "") ? "x" : matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private String separator(String keyStart) {
        String key = "";
        for (int i = 0; i < keyStart.length(); i++) {
            if (!(originalAlphabet.contains(String.valueOf(keyStart.charAt(i))))) {
                throw new RuntimeException();
            }
        }
        for (int i = 0; i < keyStart.length(); i++) {
            if (keyStart.charAt(i) == 'j') {
                key += !key.contains("i") ? "i" : "";
            } else {
                key += !key.contains(String.valueOf(keyStart.charAt(i))) ? keyStart.charAt(i) : "";
            }
        }
        return key;
    }

    private String uniqueSymbolsInAlphabet(String alphabet, String key) {
        for (int i = 0; i < getKey().length(); i++) {
            if (alphabet.contains(String.valueOf(key.charAt(i)))) {
                alphabet = alphabet.replace(String.valueOf(key.charAt(i)), "");
            }
        }
        return alphabet;
    }

    public void analise(String key) {
        bigrams = new String[key.length() % 2 == 0 ? key.length() / 2 : key.length() / 2 + 1];
        String[] bigrams = this.bigrams;

        boolean isFiller = key.length() % 2 != 0;
        int amountOfIterations = !isFiller ? key.length() / 2 : key.length() / 2 + 1;

        for (int i = 0; i < amountOfIterations; i++) {
            if (isFiller && i == amountOfIterations - 1) {
                bigrams[i] = key.charAt(i*2) + "z";
            } else {
                bigrams[i] = "" + key.charAt(i * 2) + key.charAt(i * 2 + 1);
            }
        }

        this.bigrams = bigrams;
    }

    public void finalCode() {
        System.out.print("Final result: ");
        for (int i = 0; i < bigrams.length; i++) {
            System.out.print(codeOneBigram(bigrams[i])[0] + codeOneBigram(bigrams[i])[1]);
        }
    }

    private String[] codeOneBigram(String src) {

        //0 - y, 1 - x

        String[] answer = new String[2];

        String letter1 = String.valueOf(src.charAt(0));
        String letter2 = String.valueOf(src.charAt(1));

        int[] letter1Coords = findElemCoords(letter1);
        int[] letter2Coords = findElemCoords(letter2);

        if (letter1Coords[0] == letter2Coords[0]) {
            letter1Coords[1] = letter1Coords[1] != 4 ? letter1Coords[1] + 1 : 0;
            letter2Coords[1] = letter2Coords[1] != 4 ? letter2Coords[1] + 1 : 0;
        } else if (letter1Coords[1] == letter2Coords[1]) {
            letter1Coords[0] = letter1Coords[0] != 4 ? letter1Coords[0] + 1 : 0;
            letter2Coords[0] = letter2Coords[0] != 4 ? letter2Coords[0] + 1 : 0;
        } else {
            int temp = letter1Coords[0];
            letter1Coords[0] = letter2Coords[0];
            letter2Coords[0] = temp;
        }

        answer[0] = matrix[letter1Coords[1]][letter1Coords[0]];
        answer[1] = matrix[letter2Coords[1]][letter2Coords[0]];

        return answer;
    }

    private int[] findElemCoords(String letter) {
        int[] coords = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (Objects.equals(matrix[i][j], letter)) {
                    coords[0] = j;
                    coords[1] = i;
                }
            }
        }
        return coords;
    }
}
