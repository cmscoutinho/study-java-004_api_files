package ex_03_10.ex_02.utils;

import ex_03_10.ex_02.exception.InvalidPasswordException;

import java.util.Scanner;

public class PasswordChecker {

    private String readPasswd() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Password: ");
        return scanner.next();
    }

    public boolean auth() {
        String usrPasswd = readPasswd();
        if (usrPasswd.length() < 8) {
            throw new InvalidPasswordException("Your password must have at least 8 characters!");
        } else {
            return true;
        }
    }
}
