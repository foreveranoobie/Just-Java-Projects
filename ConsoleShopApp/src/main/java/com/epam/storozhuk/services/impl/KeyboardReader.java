package com.epam.storozhuk.services.impl;

import com.epam.storozhuk.services.Reader;

import java.util.Scanner;

public class KeyboardReader implements Reader {
    private Scanner scanner;

    public KeyboardReader() {
        scanner = new Scanner(System.in);
    }

    private void resetPointer() {
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public String next() {
        return scanner.next();
    }

    @Override
    public boolean hasNextInt() {
        return scanner.hasNextInt();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public int nextInt() {
        int value = scanner.nextInt();
        resetPointer();
        return value;
    }
}
