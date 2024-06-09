package ex_03_10.ex_01;

import java.util.Scanner;

public class DivisionByZero {
    public static void main(String[] args) {
        int n1, n2;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type in a number: ");
        n1 = scanner.nextInt();

        System.out.print("Type in another number: ");
        n2 = scanner.nextInt();

        try {
            System.out.println("%d/%d = %d".formatted(n1, n2, n1/n2));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
