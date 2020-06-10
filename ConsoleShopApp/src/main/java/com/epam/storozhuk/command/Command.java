package com.epam.storozhuk.command;

import com.epam.storozhuk.exceptions.ApplicationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public interface Command {
    String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

    void execute() throws ApplicationException;

    default boolean isCorrectDate(String date) {
        try {
            simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
