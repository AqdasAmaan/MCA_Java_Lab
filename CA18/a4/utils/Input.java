package utils;

import java.util.Scanner;

public class Input {
    private static Scanner sc = new Scanner(System.in);

    public static Scanner getScannerObject() {
        return sc;
    }

    public static void closeScanner() {
        sc.close();
    }
}
