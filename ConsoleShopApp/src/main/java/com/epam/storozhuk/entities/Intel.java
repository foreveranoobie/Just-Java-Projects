package com.epam.storozhuk.entities;

import com.epam.storozhuk.annotation.UserFriendlyNaming;
import com.epam.storozhuk.util.BundleKeys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Intel extends CPU implements Serializable {
    private static final long serialVersionUID = -7718149684854614620L;
    @UserFriendlyNaming(userFriendlyName = BundleKeys.INTEL_GENERATION)
    private String generation;
    @UserFriendlyNaming(userFriendlyName = BundleKeys.INTEL_MODEL)
    private String model;

    public Intel() {
    }

    public Intel(int coresCount, int frequency, BigDecimal price, String article, String countryProduction, String generation, String model) {
        super(coresCount, frequency, price, article, countryProduction);
        setGeneration(generation);
        setModel(model);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        return "Intel{" +
                "generation=" + generation +
                ", model=" + model + ", cores count=" + super.getCoresCount() + ", frequency=" + super.getFrequency() +
                ", price=" + super.getPrice() + ", country of production=" + super.getCountryProduction()
                + ", article number=" + super.getArticle() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Intel intel = (Intel) o;
        return Objects.equals(getGeneration(), intel.getGeneration()) &&
                Objects.equals(getModel(), intel.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGeneration(), getModel());
    }
}
