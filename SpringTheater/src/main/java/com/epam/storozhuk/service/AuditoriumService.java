package com.epam.storozhuk.service;

import java.util.List;
import com.epam.storozhuk.domain.Auditorium;

public interface AuditoriumService {
    List<Auditorium> getAll();

    Auditorium getByNumber(int number);
}
