import java.io.IOError;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Type key word: ");
        boolean flag1 = true;
        Matrix matrix = new Matrix(scan.nextLine());
        matrix.fill(matrix.getKey());
        matrix.printMatrix();

        boolean flag = true;
        while (flag) {
            System.out.println("To end program type \"!exit\"");
            System.out.print("Type what to code: ");
            String word = scan.nextLine();
            if(Objects.equals(word, "!exit")) {
                flag = false;
            } else {
                try {
                    matrix.analise(word);
                    matrix.finalCode();
                } finally {
                    System.out.println("Wrong input");
                }
            }
        }
    }
}