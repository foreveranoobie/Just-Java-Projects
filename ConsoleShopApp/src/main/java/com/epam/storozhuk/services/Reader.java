package com.epam.storozhuk.services;

public interface Reader {
    boolean hasNext();

    boolean hasNextLine();

    boolean hasNextInt();

    String next();

    String nextLine();

    int nextInt();

}
