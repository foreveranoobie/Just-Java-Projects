package com.epam.storozhuk.entities;

import com.epam.storozhuk.annotation.UserFriendlyNaming;
import com.epam.storozhuk.util.BundleKeys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class VideoCard extends Hardware implements Serializable {
    private static final long serialVersionUID = 3135831588813946413L;
    @UserFriendlyNaming(userFriendlyName = BundleKeys.VIDEOCARD_MEMORY)
    private int memorySize;
    @UserFriendlyNaming(userFriendlyName = BundleKeys.VIDEOCARD_BUS)
    private int busMemory;

    public VideoCard() {
    }

    public VideoCard(int memorySize, int busMemory, BigDecimal price, String article, String countryProduction) {
        super(price, article, countryProduction);
        setMemorySize(memorySize);
        setBusMemory(busMemory);
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int getBusMemory() {
        return busMemory;
    }

    public void setBusMemory(int busMemory) {
        this.busMemory = busMemory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VideoCard videoCard = (VideoCard) o;
        return getMemorySize() == videoCard.getMemorySize() &&
                getBusMemory() == videoCard.getBusMemory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMemorySize(), getBusMemory());
    }

    @Override
    public String toString() {
        return "VideoCard{" +
                "memory size=" + memorySize +
                ", bus memory=" + busMemory + ", price=" + super.getPrice() + ", country of production=" + super.getCountryProduction() +
                ", article number=" + super.getArticle() + '}';
    }
}
