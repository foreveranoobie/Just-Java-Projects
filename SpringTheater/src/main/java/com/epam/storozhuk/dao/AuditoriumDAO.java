package com.epam.storozhuk.dao;

import java.util.ArrayList;
import java.util.List;
import com.epam.storozhuk.domain.Auditorium;

public class AuditoriumDAO {
    private List<Auditorium> auditoriumList;

    public AuditoriumDAO(List<Auditorium> auditoriums) {
        this.auditoriumList = new ArrayList<>(auditoriums);
    }

    public List<Auditorium> getAll() {
        return auditoriumList;
    }

    public Auditorium getByNumber(int number) {
        for (Auditorium auditorium : auditoriumList) {
            if (auditorium.getNumber() == number) {
                return auditorium;
            }
        }
        return null;
    }
}
