package com.epam.storozhuk.domain;

import java.util.Objects;

public class Auditorium {
    private int number;
    private int seatsCount;
    private int vipSeatsCount;

    public Auditorium(int number, int seatsCount, int vipSeatsCount) {
        this.number = number;
        this.seatsCount = seatsCount;
        this.vipSeatsCount = vipSeatsCount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public int getVipSeatsCount() {
        return vipSeatsCount;
    }

    public void setVipSeatsCount(int vipSeatsCount) {
        this.vipSeatsCount = vipSeatsCount;
    }

    public boolean isVipSeat(int number) {
        if (number > 0 && number <= vipSeatsCount) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditorium that = (Auditorium) o;
        return getNumber() == that.getNumber() &&
                getSeatsCount() == that.getSeatsCount() &&
                getVipSeatsCount() == that.getVipSeatsCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getSeatsCount(), getVipSeatsCount());
    }
}
