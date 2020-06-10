package com.epam.storozhuk.entities;

import com.epam.storozhuk.annotation.UserFriendlyNaming;
import com.epam.storozhuk.util.BundleKeys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CPU extends Hardware implements Serializable {
    private static final long serialVersionUID = -3883636597943840830L;
    @UserFriendlyNaming(userFriendlyName = BundleKeys.CPU_CORES_COUNT)
    private int coresCount;
    @UserFriendlyNaming(userFriendlyName = BundleKeys.CPU_FREQUENCY)
    private int frequency;

    public CPU() {
        coresCount = 0;
        frequency = 0;
    }

    public CPU(int coresCount, int frequency, BigDecimal price, String article, String countryProduction) {
        super(price, article, countryProduction);
        setCoresCount(coresCount);
        setFrequency(frequency);
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getCoresCount() {
        return coresCount;
    }

    public void setCoresCount(int coresCount) {
        this.coresCount = coresCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CPU cpu = (CPU) o;
        return getCoresCount() == cpu.getCoresCount() &&
                getFrequency() == cpu.getFrequency();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCoresCount(), getFrequency());
    }

    @Override
    public String toString() {
        return "CPU{" +
                "coresCount=" + coresCount +
                ", frequency=" + frequency + ", price=" + super.getPrice() + ", country of production=" + super.getCountryProduction() +
                ", article number=" + super.getArticle() + '}';
    }
}
