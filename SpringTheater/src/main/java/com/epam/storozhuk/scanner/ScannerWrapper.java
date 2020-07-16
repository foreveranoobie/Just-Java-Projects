package com.epam.storozhuk.scanner;

import java.util.Scanner;

public class ScannerWrapper {
    private Scanner scanner;

    public ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

}
