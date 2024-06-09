package ex_03_10.ex_02.main;

import ex_03_10.ex_02.exception.InvalidPasswordException;
import ex_03_10.ex_02.utils.PasswordChecker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PasswordChecker passCheck = new PasswordChecker();
        try {
            if (passCheck.auth()) System.out.println("Password successfully saved!");
        } catch (InvalidPasswordException e) {
            System.out.println(e.getMessage());
        }
    }
}
