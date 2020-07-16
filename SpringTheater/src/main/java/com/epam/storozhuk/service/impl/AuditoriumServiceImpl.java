package com.epam.storozhuk.service.impl;

import java.util.List;
import com.epam.storozhuk.dao.AuditoriumDAO;
import com.epam.storozhuk.domain.Auditorium;
import com.epam.storozhuk.service.AuditoriumService;

public class AuditoriumServiceImpl implements AuditoriumService {
    private AuditoriumDAO auditoriumDAO;

    public AuditoriumServiceImpl(AuditoriumDAO auditoriumDAO) {
        this.auditoriumDAO = auditoriumDAO;
    }

    @Override
    public List<Auditorium> getAll() {
        return auditoriumDAO.getAll();
    }

    @Override
    public Auditorium getByNumber(int number) {
        return auditoriumDAO.getByNumber(number);
    }
}
